package net.atos.api.cliente.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.NotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.atos.api.cliente.domain.DeletaClienteVO;
import net.atos.api.cliente.repository.ClienteRepository;
import net.atos.api.cliente.repository.entity.ClienteEntity;


@Service
public class DeletaClienteService {


    private Validator validator;

    private ClienteRepository clienteRepository;


    public DeletaClienteService(Validator pValidator,
    		ClienteRepository pclienteRepository) {
        this.validator = pValidator;
        this.clienteRepository = pclienteRepository;
    }
    
    @Transactional
    public void deletar(DeletaClienteVO deletaClienteVO) {
        Set<ConstraintViolation<DeletaClienteVO>>
                validateMessages = this.validator.validate(deletaClienteVO);

        if (!validateMessages.isEmpty()) {
            throw new ConstraintViolationException("Cadastro inválido", validateMessages);
        }

        ClienteEntity clienteEncontrado = clienteRepository.findById(deletaClienteVO.getIdCliente())
                .orElseThrow(()-> new NotFoundException("Cadastro cliente não encontrado. idCliente="+deletaClienteVO.getIdCliente()));
    }

    }

