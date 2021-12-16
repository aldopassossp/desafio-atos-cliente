package net.atos.api.cliente.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.atos.api.cliente.domain.ClienteVO;
import net.atos.api.cliente.service.AtualizaClienteService;
import net.atos.api.cliente.service.BuscaClienteService;
import net.atos.api.cliente.service.CadastroClienteService;
import net.atos.api.cliente.service.DeletaClienteService;

@RestController
@RequestMapping("/v1/clientes")
@Tag(name = "Cliente")
public class ClienteController {
	
//	private List<CadastroClienteService> cadastroClienteStrategies;

	private BuscaClienteService buscaClienteService;

	private DeletaClienteService deletaClienteService;
	
	private CadastroClienteService cadastroClienteService;
	
	private AtualizaClienteService atualizaClienteService;

//	public ClienteController(List<CadastroClienteService> strategies, BuscaClienteService buscaNotaFiscalService,
//			CancelaNotaFiscalVendaService cancelaService) {
//		super();
//		this.buscaNotaFiscalService = buscaNotaFiscalService;
//		this.criacaoNotaFiscalStrategies = strategies;
//		this.cancelaNotaFiscalVendaService = cancelaService;
//	}
	public ClienteController(BuscaClienteService buscaClienteService, CadastroClienteService cadastroClienteService,
			DeletaClienteService deletaClienteService) {
		super();
		this.buscaClienteService = buscaClienteService;
		this.cadastroClienteService = cadastroClienteService;
		this.deletaClienteService = deletaClienteService;
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON }, value = "/cadastrocliente")
	@Operation(description = "Cadastra Cliente")
	public ResponseEntity<ClienteVO> cadastroCliente(@Valid @RequestBody ClienteVO clienteVO) {
		
		
		System.out.println("###########################################################");
		System.out.println(clienteVO.getDataCriacao());
		
		clienteVO.setDataCriacao(LocalDateTime.now());
		
		System.out.println(clienteVO.getDataCriacao());
		
		ClienteVO clienteCreated = cadastroClienteService.cadastrarCliente(clienteVO);
		
		System.out.println(clienteCreated.getDataCriacao());
		
		URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}")
				.buildAndExpand(clienteCreated.getId()).toUri();

		return ResponseEntity.created(uri).body(clienteCreated);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON })
	@Operation(description = "Consulta cliente por id")
	public ResponseEntity<ClienteVO> getClientePorId(@PathVariable("id") Long id) {

		ClienteVO clienteEncontrado = (ClienteVO) buscaClienteService.porId(id);

		return ResponseEntity.ok(clienteEncontrado);
	}

	@PutMapping(produces = { MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON }, value = "/{id}")
	@Operation(description = "Atualiza Cliente")
	public ResponseEntity<?> atualizaCliente(@Valid @PathVariable Long id, @RequestBody ClienteVO clienteVO) {

		Optional<ClienteVO> clienteAtual = Optional.ofNullable(buscaClienteService.porId(id));
		
		if(clienteAtual.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		try {
			BeanUtils.copyProperties(clienteVO, clienteAtual.get(), "id");
			ClienteVO clienteSalvo = atualizaClienteService.atualizarCliente(clienteAtual.get());
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
		} catch (Exception e ) {
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}
		

	}

//	@GetMapping(value = "/documentos/{documento}", produces = { MediaType.APPLICATION_JSON })
//	@Operation(description = "Consulta notas fiscais por documento")
//	public ResponseEntity<List<ClienteVO>> getNotaFiscaisPorDocumentos(@PathVariable("documento") String documento) {
//
//		List<NotaFiscalVO> notasFiscaisEncontradas = this.buscaClienteService.porDocumento(documento);
//
//		return ResponseEntity.ok(notasFiscaisEncontradas);
//
//	}
//
//	@PageableBinding
//	@GetMapping(value = "/emissao-periodos/{dataInicio}/{dataFim}", produces = { MediaType.APPLICATION_JSON })
//	@Operation(description = "Consulta notas fiscais por período")
//	public ResponseEntity<Page<NotaFiscalVO>> getNotaFiscaisPorPeriodo(
//			@PathVariable("dataInicio") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataInicio,
//			@PathVariable("dataFim") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataFim,
//			@ParameterObject @PageableDefault(sort = {
//					"dataEmissao" }, direction = Direction.DESC, page = 0, size = 10) Pageable pageable) {
//
//		Page<NotaFiscalVO> notasFiscaisEncontradas = this.buscaNotaFiscalService.porPeriodoDataEmissao(dataInicio,
//				dataFim, pageable);
//
//		return ResponseEntity.ok(notasFiscaisEncontradas);
//
//	}

}
