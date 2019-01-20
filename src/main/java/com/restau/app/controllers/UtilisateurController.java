package com.restau.app.controllers;

import java.security.NoSuchAlgorithmException;
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

import com.restau.app.models.Utilisateur;
import com.restau.app.services.UtilisateurService;

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
@RequestMapping("/utilisateur")
@CrossOrigin("*")
@Api(value = "onlinestore", description = "Permet de faire des opérations sur les utilisateurs")
public class UtilisateurController {

	@Autowired
	UtilisateurService utilisateurService;
	
	@ApiOperation(value = "Show users list", response = Iterable.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
			})
	@GetMapping("")
	public List<Utilisateur> getAllUtilisateur() {
		return utilisateurService.findAllUtilisateur();
	}

	@ApiOperation(value = "Create utilisateur",response = Utilisateur.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved object"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
			})
	@PostMapping("")
	public Utilisateur createUtilisateur(@Valid @RequestBody Utilisateur utilisateur) throws NoSuchAlgorithmException {
		return utilisateurService.saveUtilisateur(utilisateur);
	}
	
	@ApiOperation(value = "login with an name",response = Utilisateur.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved object"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
			})
	@PostMapping(value = "/login")
	public Utilisateur login(@RequestBody Utilisateur user) throws NoSuchAlgorithmException {
		return utilisateurService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
	
	@ApiOperation(value = "Delete utilisateur with an ID",response = Boolean.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved true"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
			})
	@DeleteMapping(value = "/{id}")
	public boolean deleteUtilisateur(@PathVariable("id") String id) {
		return utilisateurService.deleteUtilisateur(id);
	}
	
	@ApiOperation(value = "Update utilisateur",response = Utilisateur.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved object"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
			})
	@PutMapping(value = "/{id}")
	public Utilisateur updateUtilisateur(@PathVariable("id") String id, @Valid @RequestBody Utilisateur utilisateur) throws NoSuchAlgorithmException {
		return utilisateurService.modifyUtilisateur(id, utilisateur);
	}
}
