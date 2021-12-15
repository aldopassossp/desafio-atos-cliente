package net.atos.api.cliente.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import net.atos.api.cliente.domain.ClienteVO;
import net.atos.api.cliente.domain.EnderecoVO;
import net.atos.api.cliente.repository.entity.ClienteEntity;
import net.atos.api.cliente.repository.entity.EnderecosEntity;
import net.atos.api.cliente.repository.entity.EnderecosPK;

public class ClienteFactory {
	
	private ClienteVO vo;
	private ClienteEntity entity;
	
	public ClienteFactory(ClienteVO pVo) {
		this.entity = this.transformaEntity(pVo);
		this.vo = pVo;		
	}
	
	public ClienteFactory(ClienteEntity pEntity) {
		this.entity = pEntity;
		this.vo = this.transformaVO(pEntity);		
	}

	private ClienteVO transformaVO(ClienteEntity pEntity) {
		ClienteVO clienteVO = new ClienteVO();
		clienteVO.setId(pEntity.getId());
		clienteVO.setNome(pEntity.getNome());
		clienteVO.setTipoPessoa(pEntity.getTipoPessoa());
		clienteVO.setDocPrincipal(pEntity.getDocPrincipal());
		clienteVO.setTelefone(pEntity.getTelefone());
		clienteVO.setCelular(pEntity.getCelular());
		clienteVO.setEmail(pEntity.getEmail());
		clienteVO.setDataNascimento(pEntity.getDataNascimento());
		clienteVO.setDataCriacao(pEntity.getDataCriacao());
		
		AtomicInteger numeroEndereco = new AtomicInteger();
		List<EnderecosEntity> enderecos = Optional.ofNullable(pEntity.getEnderecos()).orElseGet(ArrayList::new);
		enderecos.stream().forEach(endereco -> 
				this.construirEnderecoVO(clienteVO, numeroEndereco, endereco));
		
		return clienteVO;
	}
	
	private void construirEnderecoVO(ClienteVO clienteVO, AtomicInteger numeroEndereco, EnderecosEntity enderecoEntity) {
		EnderecoVO enderecoVO = new EnderecoVO();
		enderecoVO.setRua(enderecoEntity.getRua());
		enderecoVO.setNumero(enderecoEntity.getNumero());
		enderecoVO.setComplemento(enderecoEntity.getComplemento());
		enderecoVO.setBairro(enderecoEntity.getBairro());
		enderecoVO.setCidade(enderecoEntity.getCidade());
		enderecoVO.setEstado(enderecoEntity.getEstado());
		enderecoVO.setCep(enderecoEntity.getCep());
		enderecoVO.setDataCriacao(enderecoEntity.getDataCriacao());
		enderecoVO.setEndPadrao(enderecoEntity.isEndPadrao());
		
		clienteVO.add(enderecoVO);
	}
	
	private ClienteEntity transformaEntity(ClienteVO cliente) {
		ClienteEntity clienteEntity = new ClienteEntity();
		clienteEntity.setId(cliente.getId());
		clienteEntity.setNome(cliente.getNome());
		clienteEntity.setTipoPessoa(cliente.getTipoPessoa());
		clienteEntity.setDocPrincipal(cliente.getDocPrincipal());
		clienteEntity.setTelefone(cliente.getTelefone());
		clienteEntity.setCelular(cliente.getCelular());
		clienteEntity.setEmail(cliente.getEmail());
		clienteEntity.setDataNascimento(cliente.getDataNascimento());
		clienteEntity.setDataCriacao(cliente.getDataCriacao());
		
		AtomicInteger numeroEndereco = new AtomicInteger();
		List<EnderecoVO> enderecos = Optional.ofNullable(cliente.getEnderecos()).orElseGet(ArrayList::new);
		enderecos.stream().forEach(endereco -> 
				this.construirEnderecoEntity(clienteEntity, numeroEndereco, endereco));
		
		return clienteEntity;
		
	}
	
	private void construirEnderecoEntity(ClienteEntity clienteEntity, AtomicInteger numeroEndereco, EnderecoVO enderecoVO) {
		EnderecosEntity enderecoEntity = new EnderecosEntity();
		enderecoEntity.setId(new EnderecosPK());
		enderecoEntity.getId().setIdEnd(numeroEndereco.incrementAndGet());
		enderecoEntity.setRua(enderecoVO.getRua());
		enderecoEntity.setNumero(enderecoVO.getNumero());
		enderecoEntity.setComplemento(enderecoVO.getComplemento());
		enderecoEntity.setBairro(enderecoVO.getBairro());
		enderecoEntity.setCidade(enderecoVO.getCidade());
		enderecoEntity.setEstado(enderecoVO.getEstado());
		enderecoEntity.setCep(enderecoVO.getCep());
		enderecoEntity.setDataCriacao(enderecoVO.getDataCriacao());
		enderecoEntity.setEndPadrao(enderecoVO.isEndPadrao());
		
		clienteEntity.add(enderecoEntity);
		
	}
	
	public ClienteEntity toEntity() {		
		return this.entity;
	}

	public ClienteVO toVO() {
		
		return this.vo;
	}
}
