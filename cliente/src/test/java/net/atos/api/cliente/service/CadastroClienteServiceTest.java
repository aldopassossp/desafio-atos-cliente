
package net.atos.api.cliente.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationEventPublisher;

import net.atos.api.cliente.domain.ClienteVO;
import net.atos.api.cliente.domain.EnderecoVO;
import net.atos.api.cliente.domain.TipoPessoaEnum;
import net.atos.api.cliente.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CadastroClienteServiceTest {

	private CadastroClienteService cadastroclienteService;

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
		cadastroclienteService = new CadastroClienteService(validator, clienteRepository);
	}

	@Test
	@DisplayName("Testa quando o Cliente for nulo")
	
	void test_QuandoClienteEhNull_LancarExcecao() {
		assertNotNull(cadastroclienteService);

		ClienteVO clienteVO = null;

		var assertThrows = assertThrows(IllegalArgumentException.class, () ->
		cadastroclienteService.cadastrarCliente(clienteVO));

		assertNotNull(assertThrows);

}

	@Test
	@DisplayName("Testa os campos que são obrigatórios para o Cliente")
	void test_quandoNaoPassaAtributosObrigatorios_LancarExcecao() {
		
		assertNotNull(cadastroclienteService);
		
		ClienteVO clienteVO = new ClienteVO();
		
		var assertThrows = assertThrows(ConstraintViolationException.class, ()->
		cadastroclienteService.cadastrarCliente(clienteVO));
		
		assertEquals(8, assertThrows.getConstraintViolations().size());
		List<String> mensagens = assertThrows.getConstraintViolations()
				.stream()
				.map(ConstraintViolation::getMessage).
				collect(Collectors.toList());
		
		assertThat(mensagens, hasItems("Campo nome não pode ser nulo e nem vazio",
				"Campo Tipo Pessoa não pode ser nulo e nem vazio",
				"Campo docPrincipal não pode ser nulo e nem vazio",
				"Campo telefone não pode ser nulo e nem vazio",
				"Campo e-mail não pode ser nulo e nem vazio",
				"Campo DataNascimento não pode ser nulo",
				"Campo DataCriacao não pode ser nulo",
				"Campo Endereço não pode ser nulo"
				));
		
	//	var exception = assertThrows(ConstraintViolationException.class, () ->
	//	cadastroclienteService.cadastrarCliente(clienteVO));

	//	assertNotNull(exception);
	}


	@Test
	void test_quandoDataDeCriacaoDiferente_LancaExcecao () {
		assertNotNull(cadastroclienteService);

		ClienteVO cliente = new ClienteVO();

		cliente.setDataCriacao(LocalDate.now().minusDays(1l));
		cliente.setNome("Projeto");
		cliente.setTipoPessoa(TipoPessoaEnum.FISICA);
		cliente.setDocPrincipal("09600015270");
		cliente.setTelefone("81996002547");
		cliente.setEmail("Projeto@teste.com");
		cliente.setDataNascimento(LocalDate.now());
		
		EnderecoVO endereco = new EnderecoVO();
		endereco.setRua("Rua A");
		endereco.setNumero("2");
		endereco.setComplemento("Casa");
		endereco.setBairro("Boa Viagem");
		endereco.setCidade("Recife");
		endereco.setEstado("PE");
		endereco.setCep("55400000");
		endereco.setEndPadrao(false);
		
		cliente.add(endereco);

		var assertThrows = assertThrows(BadRequestException.class, ()->
		cadastroclienteService.cadastrarCliente(cliente));

		assertEquals(assertThrows.getMessage(), "A data de criação deve ser atual");
	}

	@Test
	void testa_quandoDadosPreenchidos_CadastroCliente() {
		assertNotNull(cadastroclienteService);

		ClienteVO cliente = new ClienteVO();
		cliente.setId(1);
		cliente.setNome(" ");
		cliente.setTipoPessoa(TipoPessoaEnum.FISICA);
		cliente.setDocPrincipal(" ");
		cliente.setTelefone(" ");
		cliente.setCelular(" ");
		cliente.setEmail(" ");
		cliente.setDataNascimento(LocalDate.now());
		cliente.setDataCriacao(LocalDate.now());

		EnderecoVO endereco = new EnderecoVO();
		endereco.setRua("");
		endereco.setNumero("2");
		endereco.setBairro(" ");
		endereco.setCidade(" ");
		endereco.setEstado(" ");
		endereco.setCep(" ");

	}

}