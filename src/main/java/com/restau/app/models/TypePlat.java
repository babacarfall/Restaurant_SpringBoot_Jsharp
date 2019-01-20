package com.restau.app.models;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @since 24/12/2018
 * @version 1.0
 * @author JSHARP
 *
 */
@Document(collection = "typeplats")
@JsonIgnoreProperties(value = { "createdAt" }, allowGetters = true)
@Data
public class TypePlat {
	@Id
    @ApiModelProperty(notes = "The database generated classe ID")
	private String id;

	@NotBlank
	@Size(max = 100)
    @Indexed(unique=true)
	@ApiModelProperty(notes = "The name of the type plat", required = true)
	@Field("nom")
	private String nom;
	
	private Date createdAt;
	
	
	public TypePlat() {
		this.createdAt=new Date();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("TypePlat[id=%s,nom=%s]", id,nom);
	}
}
