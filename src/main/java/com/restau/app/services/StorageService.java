package com.restau.app.services;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	//private final Path rootLocation = Paths.get("upload-dir");
	private final String DIRECTORY = "upload-dir/";
	private final String DIRECTOR = "upload-dir";

	public String store(MultipartFile file) throws IOException {
		
			String nom=""+new Date().getTime();
			if(file.getOriginalFilename().lastIndexOf(".")>-1)
				nom+=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			InputStream in = file.getInputStream();
			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			File targetFile = new File(DIRECTORY+nom);
		    OutputStream outStream = new FileOutputStream(targetFile);
		    outStream.write(buffer);
		    outStream.flush();
		    outStream.close();
		    return nom;		
	}
	public Resource loadFile(String filename) {
		try {
			File file=new File(DIRECTORY+filename);
			Resource resource = new UrlResource(file.toURI());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Le fichier n'existe pas");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}
	public void deleteFile(String filename) {
			File file = new File(DIRECTORY+filename);
			if (file.exists()) {
				file.delete();
			} else {
				throw new RuntimeException("Le fichier n'existe pas");
			}
	}
	
	public void init() {
		File theDir = new File(DIRECTOR);
		if (!theDir.exists()) {
		    theDir.mkdir();
		    return;
		}
		if(!theDir.isDirectory()) {
			theDir.delete();
			theDir.mkdirs();
			return;
		}
	}
	public List<String> getAllFile(){
		List<String>liste=new ArrayList<>();
		File theDir = new File(DIRECTOR);
		if(theDir.isDirectory())
			for(String filename:theDir.list())
			liste.add(filename);
		return liste;
	}
	public void deleteAllFile() {
		for(String filename:getAllFile())
			deleteFile(filename);
	}
	
	/*public void store(MultipartFile file) {
		try {
			String nom=""+new Date().getTime();
			if(file.getOriginalFilename().lastIndexOf(".")>-1)
				nom+=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			FileWriter fichier =new FileWriter(nom);
			byte[] buffer = new byte[file.getInputStream().available()];
			InputStream in = file.getInputStream();
			in.read(buffer);
			File targetFile = new File(DIRECTORY+nom);
		    OutputStream outStream = new FileOutputStream(targetFile);
		    outStream.write(buffer);
		    outStream.flush();
		    outStream.close();
			
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}
	}*/
/*
	public Resource loadFile(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}

	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}*/
}