package com.educaweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educaweb.course.dto.PaymentDTO;
import com.educaweb.course.dto.PaymentDTO;
import com.educaweb.course.services.PaymentService;

@RestController
@RequestMapping("/payments" )
public class PaymentResource {
	
	@Autowired
	private PaymentService service;
	

	
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<PaymentDTO>> findAll(){
		List<PaymentDTO> dtos = service.findAll();
		//return ResponseEntity.ok().body(u);
		return ResponseEntity.ok().body(dtos);
	}
	
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<PaymentDTO> findId(@PathVariable Long id){
		PaymentDTO dto = service.findById(id);
		//return ResponseEntity.ok().body(u);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<PaymentDTO> insert(@RequestBody PaymentDTO dto) {
		PaymentDTO  newDto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();

		return ResponseEntity.created(uri).body(newDto);

	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<PaymentDTO> update(@PathVariable Long id, @RequestBody PaymentDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	

}
