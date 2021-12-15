package net.atos.api.cliente.service;

import net.atos.api.cliente.domain.DeletaClienteVO;
import net.atos.api.cliente.repository.DeletaClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.NotFoundException;
import java.util.Set;


@Service
public class DeletaClienteService {


    private Validator validator;

    private DeletaClienteRepository deletaClienteRepository;


    public DeletaClienteService(Validator pValidator,
                               DeletaClienteRepository pDeletaClienteRepository) {
        this.validator = pValidator;
        this.deletaClienteRepository = pDeletaClienteRepository;
    }
    @Transactional
    public void deletar(DeletaClienteVO deletaClienteVO) {
        Set<ConstraintViolation<DeletaClienteVO>>
                validateMessages = this.validator.validate(deletaClienteVO);

        if (!validateMessages.isEmpty()) {
            throw new ConstraintViolationException("Cadastro inválido", validateMessages);
        }

        Object clienteEncontrado = deletaClienteRepository.findByIdCliente(deletaClienteVO.getIdCliente())
                .orElseThrow(()-> new NotFoundException("Cadastro cliente não encontrado. idCliente="+deletaClienteVO.getIdCliente()));
    }

    }

