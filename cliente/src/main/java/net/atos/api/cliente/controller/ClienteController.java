package net.atos.api.cliente.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MediaType;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.atos.api.cliente.config.PageableBinding;
import net.atos.api.cliente.domain.ClienteVO;
import net.atos.api.cliente.service.CriaCliente;
import net.atos.api.cliente.service.BuscaClienteService;
import net.atos.api.cliente.service.DeletaClienteService;

@RestController
@RequestMapping("/v1/clientes")
@Tag(name = "Cliente")
public class ClienteController {

	private List<CriaCliente> criacaoClienteStrategies;

	private BuscaClienteService buscaClienteService;

	private DeletaClienteService deletaClienteService;

	public ClienteController(List<CriaCliente> strategies, BuscaClienteService buscaClienteService,
			DeletaClienteService cancelaService) {
		super();
		this.buscaClienteService = buscaClienteService;
		this.criacaoClienteStrategies = strategies;
		this.deletaClienteService = cancelaService;
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
	@Operation(description = "Cria Cliente")
	public ResponseEntity<ClienteVO> criaCliente(@Valid @RequestBody ClienteVO cliente) {

		CriaCliente criaCliente = criacaoClienteStrategies.stream()
				.filter(item -> item.isType(cliente.getTipoPessoa())).findFirst()
				.orElseThrow(() -> new BadRequestException("Tipo Pessoa Não Existe."));

		ClienteVO ClienteCreated = criaCliente.persistir(cliente);
		
		URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}")
				.buildAndExpand(ClienteCreated.getId()).toUri();

		return ResponseEntity.created(uri).body(ClienteCreated);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON })
	@Operation(description = "Consulta um cliente por id")
	public ResponseEntity<ClienteVO> getClientePorId(@PathVariable("id") Long id) {

		ClienteVO ClienteEncontrado = buscaClienteService.porId(id);

		return ResponseEntity.ok(ClienteEncontrado);
	}

	@PatchMapping(value = "/{id}/cancelamento", produces = { MediaType.APPLICATION_JSON })
	@Operation(description = "Realiza exclusão de um cliente")
	public ResponseEntity<Long>deletarClientePorId(@PathVariable("id") Long id) {

		this.deletaClienteService.deletar(id);

		return ResponseEntity.ok(id);

	}

	@GetMapping(value = "/documentos/{documento}", produces = { MediaType.APPLICATION_JSON })
	@Operation(description = "Consulta cadastros por documento")
	public ResponseEntity<List<ClienteVO>> getNotaFiscaisPorDocumentos(@PathVariable("documento") String documento) {

		List<ClienteVO> clientesEncontrados = this.buscaClienteService.porDocumento(documento);

		return ResponseEntity.ok(clientesEncontrados);

	}

	@PageableBinding
	@GetMapping(value = "/cadastro-periodos/{dataInicio}/{dataFim}", produces = { MediaType.APPLICATION_JSON })
	@Operation(description = "Consulta cadastros por período")
	public ResponseEntity<Page<ClienteVO>> getClientesPorPeriodo(
			@PathVariable("dataInicio") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataInicio,
			@PathVariable("dataFim") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataFim,
			@ParameterObject @PageableDefault(sort = {
					"dataEmissao" }, direction = Direction.DESC, page = 0, size = 10) Pageable pageable) {

		Page<ClienteVO> clientesEncontrados = this.buscaClienteService.porPeriodoCadastro(dataInicio,
				dataFim, pageable);

		return ResponseEntity.ok(clientesEncontrados);

	}

}

