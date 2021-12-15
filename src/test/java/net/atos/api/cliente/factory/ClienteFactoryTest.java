package net.atos.api.cliente.factory;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import net.atos.api.cliente.domain.ClienteVO;
import net.atos.api.cliente.domain.EnderecoVO;
import net.atos.api.cliente.domain.TipoPessoaEnum;
import net.atos.api.cliente.repository.entity.ClienteEntity;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
 public class ClienteFactoryTest {

	@Test
	@DisplayName("Testa o factory Cliente de VO para entity")
	public void test_quandoCriarEntityPeloVO_transformaParaEntity() {
		
		ClienteVO clienteVO = new ClienteVO();
		clienteVO.setNome("Projeto");
		clienteVO.setTipoPessoa(TipoPessoaEnum.FISICA);
		clienteVO.setDocPrincipal("09602400012");
		clienteVO.setTelefone("8199669966");
		clienteVO.setCelular("8199669966");
		clienteVO.setEmail("projeto@teste.com");
		clienteVO.setDataNascimento(LocalDate.now());
		clienteVO.setDataCriacao(LocalDateTime.now());
		clienteVO.setId(2l);
		
		EnderecoVO enderecoVO = new EnderecoVO();
		enderecoVO.setRua("Rua A");
		enderecoVO.setNumero((short)1001);
		enderecoVO.setComplemento("Casa");
		enderecoVO.setBairro("Boa Viagem");
		enderecoVO.setCidade("Recife");
		enderecoVO.setEstado("PE");
		enderecoVO.setEndPadrao(false);
		
		clienteVO.add(enderecoVO);
		
		
		ClienteEntity clienteEntitycriada = new ClienteFactory(clienteVO).toEntity();
		
		assertNotNull(clienteEntitycriada);
		
		assertNotNull(clienteEntitycriada.getNome());
		assertEquals(clienteVO.getNome(), clienteEntitycriada.getNome());
		
		assertNotNull(clienteEntitycriada.getTipoPessoa());
		assertEquals(clienteVO.getTipoPessoa(), clienteEntitycriada.getTipoPessoa());
		
		assertNotNull(clienteEntitycriada.getTelefone());
		assertEquals(clienteVO.getTelefone(), clienteEntitycriada.getTelefone());
		
		assertNotNull(clienteEntitycriada.getCelular());
		assertEquals(clienteVO.getCelular(), clienteEntitycriada.getCelular());
		
		assertNotNull(clienteEntitycriada.getEmail());
		assertEquals(clienteVO.getEmail(), clienteEntitycriada.getEmail());
		
		assertNotNull(clienteEntitycriada.getDataNascimento());
		assertEquals(clienteVO.getDataNascimento(), clienteEntitycriada.getDataNascimento());
		
		assertNotNull(clienteEntitycriada.getDataCriacao());
		assertEquals(clienteVO.getDataCriacao(), clienteEntitycriada.getDataCriacao());
		
		
		assertNotNull(clienteEntitycriada.getEnderecos());
		assertEquals(clienteVO.getEnderecos().size(), clienteEntitycriada.getEnderecos().size());
		
		assertEquals(clienteVO.getEnderecos().size(), clienteEntitycriada.getEnderecos().size());
		assertEquals(clienteVO.getEnderecos().get(0).getRua(), 
						clienteEntitycriada.getEnderecos().get(0).getRua());
		
		assertEquals(clienteVO.getEnderecos().get(0).getNumero(),
				clienteEntitycriada.getEnderecos().get(0).getNumero());
		
		assertEquals(clienteVO.getEnderecos().get(0).getComplemento(), 
				clienteEntitycriada.getEnderecos().get(0).getComplemento());
		
		assertEquals(clienteVO.getEnderecos().get(0).getCidade(), 
				clienteEntitycriada.getEnderecos().get(0).getCidade());
		
		assertEquals(clienteVO.getEnderecos().get(0).getEstado(), 
				clienteEntitycriada.getEnderecos().get(0).getEstado());
		
		assertEquals(clienteVO.getEnderecos().get(0).getCep(), 
				clienteEntitycriada.getEnderecos().get(0).getCep());
		
		assertEquals(clienteVO.getEnderecos().get(0).isEndPadrao(), 
				clienteEntitycriada.getEnderecos().get(0).isEndPadrao());
		
		assertEquals(clienteVO.getEnderecos().get(0).getDataCriacao(), 
				clienteEntitycriada.getEnderecos().get(0).getDataCriacao());
		
		ClienteVO voClienteCriado = new ClienteFactory(clienteEntitycriada).toVO();
		
		assertNotNull(voClienteCriado);
		assertNotNull(voClienteCriado.getNome());
		assertEquals(clienteVO.getNome(), voClienteCriado.getNome());
		
		assertNotNull(voClienteCriado.getTipoPessoa());
		assertEquals(clienteVO.getTipoPessoa(), voClienteCriado.getTipoPessoa());
		
		assertNotNull(voClienteCriado.getDocPrincipal());
		assertEquals(clienteVO.getDocPrincipal(), voClienteCriado.getDocPrincipal());
		
		assertNotNull(voClienteCriado.getTelefone());
		assertEquals(clienteVO.getTelefone(), voClienteCriado.getTelefone());
		
		assertNotNull(voClienteCriado.getCelular());
		assertEquals(clienteVO.getCelular(), voClienteCriado.getCelular());
		
		assertNotNull(voClienteCriado.getEmail());
		assertEquals(clienteVO.getEmail(), voClienteCriado.getEmail());
		
		assertNotNull(voClienteCriado.getDataNascimento());
		assertEquals(clienteVO.getDataNascimento(), voClienteCriado.getDataNascimento());
		
		assertNotNull(voClienteCriado.getEnderecos());
		assertEquals(clienteVO.getEnderecos().size(), voClienteCriado.getEnderecos().size());
		
		assertEquals(clienteVO.getEnderecos().get(0).getRua(), 
						voClienteCriado.getEnderecos().get(0).getRua());
		
		assertEquals(clienteVO.getEnderecos().get(0).getNumero(), 
				voClienteCriado.getEnderecos().get(0).getNumero());
		
		assertEquals(clienteVO.getEnderecos().get(0).getComplemento(), 
				voClienteCriado.getEnderecos().get(0).getComplemento());
		
		assertEquals(clienteVO.getEnderecos().get(0).getCidade(), 
				voClienteCriado.getEnderecos().get(0).getCidade());
		
		assertEquals(clienteVO.getEnderecos().get(0).getBairro(), 
				voClienteCriado.getEnderecos().get(0).getBairro());
		
		assertEquals(clienteVO.getEnderecos().get(0).getEstado(), 
				voClienteCriado.getEnderecos().get(0).getEstado());
		
		assertEquals(clienteVO.getEnderecos().get(0).getCep(), 
				voClienteCriado.getEnderecos().get(0).getCep());
		
		assertEquals(clienteVO.getEnderecos().get(0).isEndPadrao(), 
				voClienteCriado.getEnderecos().get(0).isEndPadrao());
		
		assertEquals(clienteVO.getEnderecos().get(0).getDataCriacao(), 
				voClienteCriado.getEnderecos().get(0).getDataCriacao());
		

		
		
		
		
		//fail("Not yet implemented");
	}

}
