package com.restau.app.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.restau.app.services.StorageService;

import io.swagger.annotations.Api;

/**
 * @since 01/02/2019
 * @author JSHARP
 * @version 1.0
 */
@RestController
@RequestMapping("/images")
@CrossOrigin("*")
@Api(value = "onlinestore", description = "Permet de faire des opérations sur les images")
public class UploadController {

	@Autowired
	StorageService storageService;

	List<String> files = new ArrayList<String>();

	@PostMapping("/post")
	public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
			return storageService.store(file);
	}

	@GetMapping("")
	public ResponseEntity<List<String>> getListFiles() {
		return ResponseEntity.ok().body(storageService.getAllFile());
	}

	/*@GetMapping("/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.loadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}*/
	@GetMapping("/{filename:.+}")
	public Resource getFile(@PathVariable String filename) {
		Resource file = storageService.loadFile(filename);
		return file;
	}

	@DeleteMapping("/{filename:.+}")
	public String deleteFile(@PathVariable String filename) {
		storageService.deleteFile(filename);
		return "Delete";
	}
}