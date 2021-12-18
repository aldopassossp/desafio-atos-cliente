package net.atos.api.cliente.service;

import net.atos.api.cliente.domain.ClienteVO;
import net.atos.api.cliente.factory.ClienteFactory;
import net.atos.api.cliente.repository.ClienteRepository;
import net.atos.api.cliente.repository.entity.ClienteEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.NotFoundException;

import java.util.Optional;
import java.util.Set;

@Service
public class BuscaClienteService {

    private Validator validator;

    private ClienteRepository clienteRepository;

   public BuscaClienteService(Validator v, ClienteRepository repository) {
       this.validator = v;
       this.clienteRepository = repository;
    }

    
    public ClienteVO porId(long id) {
    	ClienteEntity clienteEntity = this.clienteRepository.findById(id)
    	.orElseThrow(()-> new NotFoundException("Não encontrado cliente com id = "+id));

    	return new ClienteFactory(clienteEntity).toVO();
    	}
}


