package com.restau.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restau.app.models.TypePlat;
import com.restau.app.services.MessageByLocaleService;
import com.restau.app.services.TypePlatService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
/**
 * @since 25/12/2018
 * @author JSHARP
 * @version 1.0
 */
@RestController
@RequestMapping("/typeplat")
@CrossOrigin("*")
@Api(value = "onlinestore", description = "Permet de faire des opérations sur les types de plats")
public class TypePlatController {

	@Autowired
	TypePlatService typeplatService;
	@Autowired
	MessageByLocaleService messageByLocaleService;
	
	@ApiOperation(value = "#{doc.list.typeplat}", response = Iterable.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
			})
	@GetMapping("")
	public List<TypePlat> getAllTypePlat() {
		return typeplatService.findAllTypePlat();
	}

	@ApiOperation(value = "Create type plat",response = TypePlat.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved object"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
			})
	@PostMapping("")
	public TypePlat createTypePlat(@Valid @RequestBody TypePlat typeplat) {
		return typeplatService.saveTypePlat(typeplat);
	}
	
	@ApiOperation(value = "Search a type plat with an name",response = TypePlat.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved object"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
			})
	@GetMapping(value = "/{nom}")
	public TypePlat findTypePlat(@PathVariable("nom") String nom) {
		return typeplatService.findTypePlat(nom);
	}
	
	@ApiOperation(value = "Delete type plat with an ID",response = Boolean.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved true"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
			})
	@DeleteMapping(value = "/{id}")
	public boolean deleteTypePlat(@PathVariable("id") String id) {
		return typeplatService.deleteTypePlat(id);
	}
	
	@ApiOperation(value = "Update type plat",response = TypePlat.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved object"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
			})
	@PutMapping(value = "/{id}")
	public TypePlat updateTypePlat(@PathVariable("id") String id, @Valid @RequestBody TypePlat typeplat) {
		return typeplatService.modifyTypePlat(id, typeplat);
	}
}
