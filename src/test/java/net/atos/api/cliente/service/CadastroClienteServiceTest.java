
package net.atos.api.cliente.service;

import static org.junit.jupiter.api.Assertions.*;

import net.atos.api.cliente.domain.ClienteVO;
import net.atos.api.cliente.domain.EnderecoVO;
import net.atos.api.cliente.domain.TipoPessoaEnum;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import javax.validation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CadastroClienteServiceTest {

	private CadastroClienteService clienteService;

	private Validator validator;

	@BeforeAll
	public void iniciaGeral() {
		ValidatorFactory validatorFactory =
				Validation.buildDefaultValidatorFactory();

		this.validator = validatorFactory.getValidator();
	}

	@BeforeEach
	public void iniciaCadaTeste() {
		clienteService = new CadastroClienteService(this.validator);
	}

	@Test
	void testa_QuandoClienteNaoENull_LancarExcecao() {
		assertNotNull(clienteService);

		ClienteVO clienteVO = null;

		var assertThrows = assertThrows(IllegalArgumentException.class, () ->
				clienteService.cadastrarCliente(clienteVO));

		assertNotNull(assertThrows);

}

	@Test
	void testa_quandoNaoPassaAtributosObrigatorios_LancarExcecao() {
		assertNotNull(clienteService);
		ClienteVO clienteVO = new ClienteVO();
		var exception = assertThrows(ConstraintViolationException.class, () ->
				clienteService.cadastrarCliente(clienteVO));

		assertNotNull(exception);
	}

	@Test
	void testa_quandoTodosCamposENulo_LancarExcecao() {

		assertNotNull(clienteService);

		ClienteVO clienteVO = new ClienteVO();

		var assertThrows = assertThrows(ConstraintViolationException.class, () ->
				clienteService.cadastrarCliente(clienteVO));

		assertEquals(7, assertThrows.getConstraintViolations().size());
		List<String> mensagens = assertThrows.getConstraintViolations()
				.stream()
				.map(ConstraintViolation::getMessage)
				.collect(Collectors.toList());

		assertThat(mensagens, hasItems(
				"Campo nome não pode ser nulo e nem vazio"
		));

	}

	@Test
	void testa_quandoDataDeCriacaoDiferente_LancaExcecao () {
		assertNotNull(clienteService);

		ClienteVO cliente = new ClienteVO();

		cliente.setDataCriacao(LocalDateTime.now().minusDays(1l));

		var exception = assertThrows(ConstraintViolationException.class, ()->
				clienteService.cadastrarCliente(cliente));

		assertEquals(exception.getMessage(), "Cliente inválido");
	}

	@Test
	void testa_quandoDadosPreenchidos_CadastroCliente() {
		assertNotNull(clienteService);

		ClienteVO cliente = new ClienteVO();
		cliente.setId(1);
		cliente.setNome("");
		cliente.setTipoPessoa(TipoPessoaEnum.FISICA);
		cliente.setDocPrincipal("");
		cliente.setTelefone("");
		cliente.setCelular("");
		cliente.setEmail("");
		cliente.setDataNascimento(LocalDate.now());
		cliente.setDataCriacao(LocalDateTime.now());

		EnderecoVO endereco = new EnderecoVO();
		endereco.setRua("");
		endereco.setNumero((short) 1);
		endereco.setBairro("");
		endereco.setCidade("");
		endereco.setEstado("");
		endereco.setCep("");
		endereco.setDataCriacao(LocalDateTime.now());

	}

}