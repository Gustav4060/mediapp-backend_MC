/**
 * 
 */
package com.mitocode.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.dto.SignoDTO;
import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Signo;
import com.mitocode.service.ISignoService;

/**
 * @author gustavoefrainparcosanchez
 *
 */
@RestController
@RequestMapping("/signos")
public class SignoController {
	@Autowired
	private ISignoService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public ResponseEntity<List<SignoDTO>> listar() throws Exception{				
		List<SignoDTO> lista = service.listar().stream().map(p -> mapper.map(p, SignoDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SignoDTO> listarPorId(@PathVariable("id") Integer id) throws Exception{
		SignoDTO dtoResponse;
		Signo obj = service.listarPorId(id); //Signo		

		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}else {
			dtoResponse = mapper.map(obj, SignoDTO.class); //SignoDTO
		}
		
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK); 		
	}
	
	/*@PostMapping
	public ResponseEntity<SignoDTO> registrar(@Valid @RequestBody SignoDTO dtoRequest) throws Exception{
		Signo p = mapper.map(dtoRequest, Signo.class);
		Signo obj = service.registrar(p);
		SignoDTO dtoResponse = mapper.map(obj, SignoDTO.class);
		return new ResponseEntity<>(dtoResponse, HttpStatus.CREATED); 		
	}*/
	
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody SignoDTO dtoRequest) throws Exception{
		Signo p = mapper.map(dtoRequest, Signo.class);
		Signo obj = service.registrar(p);
		SignoDTO dtoResponse = mapper.map(obj, SignoDTO.class);
		//localhost:8080/Signos/1
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSigno()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<SignoDTO> modificar(@RequestBody SignoDTO dtoRequest) throws Exception {
		Signo pac = service.listarPorId(dtoRequest.getIdSigno());
		
		if(pac == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + dtoRequest.getIdSigno());	
		}
		
		Signo p = mapper.map(dtoRequest, Signo.class);
		 
		Signo obj = service.modificar(p);
		
		SignoDTO dtoResponse = mapper.map(obj, SignoDTO.class);
		
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Signo pac = service.listarPorId(id);
		
		if(pac == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		
		service.eliminar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<SignoDTO> listarHateoasPorId(@PathVariable("id") Integer id) throws Exception{
		Signo obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		
		SignoDTO dto = mapper.map(obj, SignoDTO.class);
		
		EntityModel<SignoDTO> recurso = EntityModel.of(dto);
		//localhost:8080/Signos/1
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorId(id));
		WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).listarHateoasPorId(id));
		recurso.add(link1.withRel("Signo-recurso1"));
		recurso.add(link2.withRel("Signo-recurso2"));
		
		return recurso;
	}
	
	
}
