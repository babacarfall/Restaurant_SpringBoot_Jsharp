package com.restau.app.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @since 24/12/2018
 * @version 1.0
 * @author JSHARP
 *
 */
@Document(collection = "utilisateur")
@JsonIgnoreProperties(value = { "createdAt" }, allowGetters = true)
@Data
public class Utilisateur implements Serializable {

	private static final long serialVersionUID = -2044952620977111416L;
	@Id
    @ApiModelProperty(notes = "The database generated classe ID")
	private String id;
	@NotBlank
	@Size(max = 100)
	@Size(min = 5)
    @Indexed(unique=true)
	@ApiModelProperty(notes = "username of user", required = true)
	private String username;
	@NotBlank
	@Size(min = 10)
	@Size(max = 255)
	private String password;
	private Date createdAt;
	
	public Utilisateur() {
		this.createdAt=new Date();
	}
	
	@Override
	public String toString() {
		return String.format("Utilisateur[id=%s,username=%s]", id,username);
	}

}
