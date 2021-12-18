package net.atos.api.cliente.service;

import net.atos.api.cliente.domain.ClienteVO;
import net.atos.api.cliente.factory.ClienteFactory;
import net.atos.api.cliente.repository.ClienteRepository;
import net.atos.api.cliente.repository.entity.ClienteEntity;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Service
public class CadastroClienteService {

    private Validator validator;
    
    private ClienteRepository clienteRepository; 
    

    public CadastroClienteService(Validator v, ClienteRepository repository) { 
    	this.validator = v; this.clienteRepository = repository;}

    @Transactional
    public ClienteVO cadastrarCliente(@NotNull(message = "Cliente não pode ser null") ClienteVO clienteVO) {
    	Set<ConstraintViolation<ClienteVO>>
                validateMessage = this.validator.validate(clienteVO);

        if (!validateMessage.isEmpty()) {
            throw new ConstraintViolationException("Cliente inválido", validateMessage);
            }
        
        if (!clienteVO.getDataCriacao().isEqual(LocalDate.now())) {
			throw new BadRequestException("A data de criação deve ser atual");			
		}
        
        ClienteEntity clienteEntity = new ClienteFactory(clienteVO).toEntity();

        clienteEntity = clienteRepository.save(clienteEntity);
        
        
        clienteVO.setId(clienteEntity.getId());
        
        return clienteVO;
        }
   
    }
