package net.atos.api.cliente.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.NotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.atos.api.cliente.domain.ClienteVO;
import net.atos.api.cliente.factory.ClienteFactory;
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
    public ClienteVO deletar(ClienteVO clienteVO) {
       ClienteEntity clienteEntity = new ClienteFactory(clienteVO).toEntity();
       this.clienteRepository.delete(clienteEntity);
        
        
        return clienteVO;
    }
    
    

    }

