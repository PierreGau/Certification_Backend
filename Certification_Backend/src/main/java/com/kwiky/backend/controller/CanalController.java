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
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Canal> getCanal(@PathVariable("id")Long id)
	{		
		Optional<Canal> canal = canalDirectory.getCanal(id);
		
		if(canal.isPresent())		
			return ResponseEntity.ok(canal.get());			
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
	public void deleteCanal(@PathVariable("id")Long id)
	{	
		canalDirectory.deleteCanal(id);
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
	
	
	// Recherche de canal en utilisant son nom complet
	// Récupère dans une liste tous les canaux ayant le nom indiqué en paramètre d'entrée
	@GetMapping("searchbyname")
	public List<Canal> searchByName(@RequestParam("name") String nameSearch){
		System.out.println(nameSearch);
		return canalDirectory.searchByName(nameSearch);
	}
	
	// Recherche de canal en utilisant seulement une partie de son nom
	// Par exemple, si on cherche les canaux contenant "M2i" dans leur nom, le résultat sera une liste 
	// de canaux ayant dans leur nom "M2i"
	@GetMapping("searchbynamecontains")
	public List<Canal> searchByNameContains(@RequestParam("name") String partialNameSearch){
		System.out.println(partialNameSearch);
		return canalDirectory.searchByNameContains(partialNameSearch);
	}
}
