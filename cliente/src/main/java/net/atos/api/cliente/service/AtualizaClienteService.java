package net.atos.api.cliente.service;

import net.atos.api.cliente.domain.ClienteVO;
import net.atos.api.cliente.repository.ClienteRepository;
import net.atos.api.cliente.repository.entity.ClienteEntity;

import javax.validation.Validator;
import javax.ws.rs.NotFoundException;
import java.util.List;

public class AtualizaClienteService {

    private Validator validator;

    private ClienteRepository clienteRepository;

    public AtualizaClienteService(Validator v, ClienteRepository repository) {
        this.validator = v;
        this.clienteRepository = repository;
    }

    }






