package net.atos.api.cliente.service;

import net.atos.api.cliente.domain.ClienteVO;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Service
public class CadastroClienteService {

    private final Validator validator;

    public CadastroClienteService(Validator v) { this.validator = v; }

    public void cadastrarCliente(ClienteVO clienteVO) {
        Set<ConstraintViolation<ClienteVO>>
                validateMessage = this.validator.validate(clienteVO);

        if (!validateMessage.isEmpty()) {
            throw new ConstraintViolationException("Cliente inválido", validateMessage);
            }
        }
    }
