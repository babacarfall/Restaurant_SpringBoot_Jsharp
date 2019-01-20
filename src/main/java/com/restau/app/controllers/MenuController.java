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

import com.restau.app.models.Menu;
import com.restau.app.services.MenuService;

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
@RequestMapping("/menu")
@CrossOrigin("*")
@Api(value = "onlinestore", description = "Permet de faire des opérations sur les menus")
public class MenuController {

	@Autowired
	MenuService menuService;
	
	@ApiOperation(value = "Show menu list", response = Iterable.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
			})
	@GetMapping("")
	public List<Menu> getAllMenu() {
		return menuService.findAllMenu();
	}

	@ApiOperation(value = "Create menu",response = Menu.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved object"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
			})
	@PostMapping("")
	public Menu createMenu(@Valid @RequestBody Menu menu) {
		return menuService.saveMenu(menu);
	}
	
	@ApiOperation(value = "Search a menu with an name",response = Menu.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved object"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
			})
	@GetMapping(value = "/{id}")
	public Menu getMenuById(@PathVariable("id") String id) throws Exception {
			return menuService.findById(id);
	}
	
	@ApiOperation(value = "Search a menu by id",response = Menu.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved object"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
			})
	@GetMapping(value = "/nom/{nom}")
	public Menu findMenu(@PathVariable("nom") String nom) {
		return menuService.findMenu(nom);
	}
	
	@ApiOperation(value = "Delete menu with an ID",response = Boolean.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved true"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
			})
	@DeleteMapping(value = "/{id}")
	public boolean deleteMenu(@PathVariable("id") String id) {
		return menuService.deleteMenu(id);
	}
	
	@ApiOperation(value = "Update menu",response = Menu.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved object"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
			})
	@PutMapping(value = "/{id}")
	public Menu updateMenu(@PathVariable("id") String id, @Valid @RequestBody Menu menu) {
		return menuService.modifyMenu(id, menu);
	}
}
