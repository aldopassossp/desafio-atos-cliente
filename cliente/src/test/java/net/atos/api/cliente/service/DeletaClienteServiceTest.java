package net.atos.api.cliente.service;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.NotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import net.atos.api.cliente.domain.DeletaClienteVO;
import net.atos.api.cliente.repository.ClienteRepository;


@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DeletaClienteServiceTest {

    private DeletaClienteService deletaCliente;

    private Validator validator;

    private ClienteRepository clienteRepository;

    @BeforeAll
    public void iniciaGeral() {
        ValidatorFactory validatorFactory =
                Validation.buildDefaultValidatorFactory();

        this.validator = validatorFactory.getValidator();
    }

    @BeforeEach
    public void iniciaCadaTeste() {

        this.clienteRepository = Mockito.mock(ClienteRepository.class);

        deletaCliente = new DeletaClienteService(this.validator,
                this.clienteRepository);
    }

    @Test
    public void testa_quandoNaoPassarObjetoDeletaCliente_LancarExcecao() {
        assertNotNull(deletaCliente);

        DeletaClienteVO deletaClienteVO = null;

        var exception = assertThrows(IllegalArgumentException.class,()->
                deletaCliente.deletar(deletaClienteVO));

        assertNotNull(exception);
    }

    @Test
    public void testa_quandoNaoPassarAtributosObrigatorios_LancarExcecao() {
        assertNotNull(deletaCliente);

        DeletaClienteVO deletaClienteVO = new DeletaClienteVO();

        var exception = assertThrows(ConstraintViolationException.class,()->
                deletaCliente.deletar(deletaClienteVO));

        assertNotNull(exception);
        assertEquals(1, exception.getConstraintViolations().size());

        List<String> mensagens = exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        assertThat(mensagens, hasItems("Campo id não pode ser null"));
    }

    @Test
    public void testa_quandoNaoEncontraClienteCadastrado_LancarExcecao() {
        assertNotNull(deletaCliente);

        DeletaClienteVO deletaClienteVO = new DeletaClienteVO();
        deletaClienteVO.setIdCliente(1L);

        when(this.clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception =
                assertThrows(NotFoundException.class, ()-> deletaCliente.deletar(deletaClienteVO));

        assertNotNull(exception);

        then(this.clienteRepository).should(times(1)).findById(anyLong());
    }


    }




