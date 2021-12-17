package net.atos.api.cliente.service;

import net.atos.api.cliente.domain.ClienteVO;
import net.atos.api.cliente.factory.ClienteFactory;
import net.atos.api.cliente.repository.ClienteRepository;
import net.atos.api.cliente.repository.entity.ClienteEntity;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class AtualizaClienteService {

    private Validator validator;

    private ClienteRepository clienteRepository;

    public AtualizaClienteService(Validator v, ClienteRepository repository) {
        this.validator = v;
        this.clienteRepository = repository;
    }

    
    @Transactional
    public ClienteVO atualizarCliente(@NotNull(message = "Cliente não pode ser null") ClienteVO clienteVO) {
    	
    	System.out.println("Dentro atualizarCliente");
    	System.out.println(clienteVO.toString());
        Set<ConstraintViolation<ClienteVO>>
                validateMessage = this.validator.validate(clienteVO);

        if (!validateMessage.isEmpty()) {
            throw new ConstraintViolationException("Cliente inválido", validateMessage);
            }
        
        if (!clienteVO.getDataCriacao().isEqual(LocalDate.now())) {
			throw new BadRequestException("A data de criação deve ser atual");			
		}
        
        ClienteEntity clienteEntity = new ClienteFactory(clienteVO).toEntity();
        System.out.println(clienteEntity.toString());
        clienteEntity = clienteRepository.save(clienteEntity);
        clienteVO.setId(clienteEntity.getId());
        
        return clienteVO;
        }
   
}






