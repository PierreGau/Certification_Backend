package com.kwiky.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kwiky.backend.model.Canal;
import com.kwiky.backend.service.CanalDirectory;

@RestController
@RequestMapping("api")
public class CanalController {
	@Autowired
	CanalDirectory canalDirectory;
	
	@GetMapping("canaux")
	public List<Canal> getCanaux()
	{
		return canalDirectory.getCanaux();
	}
	
	@GetMapping("canaux/{id}")
	public ResponseEntity<Canal> getCanal(@PathVariable("id")Long _id)
	{		
		Optional<Canal> o = canalDirectory.getCanal(_id);
		
		if(o.isPresent())		
			return ResponseEntity.ok(o.get());			
		else
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping("canaux")
	public Canal postCanal(@RequestBody Canal canal)
	{
		canalDirectory.addCanal(canal);
		return canal;
	}
	
	@DeleteMapping("canaux/{id}")
	public void deleteCanal(@PathVariable("id")Long _id)
	{	
		canalDirectory.deleteCanal(_id);
	}
	
	@PutMapping("canaux/{id}")
	public ResponseEntity<Canal> updateCanal(@RequestBody Canal canal, @PathVariable("id") Long id)
	{	
		if (id != canal.getId()) {
			return ResponseEntity.badRequest().build();
		} else {

			canalDirectory.putCanal(canal, id);
			return ResponseEntity.ok().build();

		}
	}
}
