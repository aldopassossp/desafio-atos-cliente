package net.atos.api.cliente.service;


import net.atos.api.cliente.domain.DeletaClienteVO;
import net.atos.api.cliente.repository.DeletaClienteRepository;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.then;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.*;
import javax.ws.rs.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DeletaClienteServiceTest {

    private DeletaClienteService deletaCliente;

    private Validator validator;

    private DeletaClienteRepository deletaClienteRepository;

    @BeforeAll
    public void iniciaGeral() {
        ValidatorFactory validatorFactory =
                Validation.buildDefaultValidatorFactory();

        this.validator = validatorFactory.getValidator();
    }

    @BeforeEach
    public void iniciaCadaTeste() {

        this.deletaClienteRepository = Mockito.mock(DeletaClienteRepository.class);

        deletaCliente = new DeletaClienteService(this.validator,
                this.deletaClienteRepository);
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

        when(this.deletaClienteRepository.findByIdCliente(anyLong())).thenReturn(Optional.empty());

        var exception =
                assertThrows(NotFoundException.class, ()-> deletaCliente.deletar(deletaClienteVO));

        assertNotNull(exception);

        then(this.deletaClienteRepository).should(times(1)).findByIdCliente(anyLong());
    }


    }




