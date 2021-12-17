package net.atos.api.cliente.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.NotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.atos.api.cliente.domain.ClienteVO;
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
    public void deletar(ClienteVO clienteVO) {
        Set<ConstraintViolation<ClienteVO>>
                validateMessages = this.validator.validate(clienteVO);

        if (!validateMessages.isEmpty()) {
            throw new ConstraintViolationException("Cadastro inválido", validateMessages);
        }

        ClienteEntity clienteEncontrado = clienteRepository.findById(clienteVO.getId())
                .orElseThrow(()-> new NotFoundException("Cadastro cliente não encontrado. idCliente="+clienteVO.getId()));
    }

    }

