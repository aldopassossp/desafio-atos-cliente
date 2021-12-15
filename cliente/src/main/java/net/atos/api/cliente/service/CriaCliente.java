package net.atos.api.cliente.service;

import javax.validation.constraints.NotNull;

import net.atos.api.cliente.domain.ClienteVO;
import net.atos.api.cliente.domain.TipoPessoaEnum;

public interface CriaCliente {

	public ClienteVO persistir(@NotNull(message = "Cliente não pode ser null") ClienteVO cliente); 

	public boolean isType(TipoPessoaEnum type);
}
