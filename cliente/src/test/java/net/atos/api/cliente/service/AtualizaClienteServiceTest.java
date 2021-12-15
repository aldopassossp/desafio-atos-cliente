package net.atos.api.cliente.service;

import static org.junit.jupiter.api.Assertions.*;

import net.atos.api.cliente.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AtualizaClienteServiceTest {

    private AtualizaClienteService atualizaCliente;

    private Validator validator;

    private ClienteRepository repository;

    @BeforeAll
    public void iniciaGeral() {
        ValidatorFactory validatorFactory =
                Validation.buildDefaultValidatorFactory();

        this.validator = validatorFactory.getValidator();
    }

    @BeforeEach
    public void iniciaCadaTeste() {

        this.repository = Mockito.mock(ClienteRepository.class);
        atualizaCliente = new AtualizaClienteService(validator, repository);
    }

    @Test
    @DisplayName("Testa quando consulta cliente por nome.")
    public void testa_quandoEncontraClientePorNome_retornaLista() {
        assertNotNull(this.atualizaCliente);


    }
}