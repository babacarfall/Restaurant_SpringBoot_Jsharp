package com.restau.app.models;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
@Document(collection = "menus")
@JsonIgnoreProperties(value = { "createdAt" }, allowGetters = true)
@Data
public class Menu {
	@Id
    @ApiModelProperty(notes = "The database generated classe ID")
	private String id;

	@NotBlank
	@Size(max = 100)
    @Indexed(unique=true)
	@Field("plat")
	@ApiModelProperty(notes = "The name of the menu", required = true)
	private String plat;
	@Field("prix")
	@ApiModelProperty(notes = "The price of menu", required = true)
	private float prix;

	@Size(max = 100)
	@ApiModelProperty(notes = "The picture path of menu")
	@Field("photo")
	private String photo;
	
	@NotBlank
	@Size(min = 10)
	@ApiModelProperty(notes = "The picture path of menu")
	@Field("desc_")
	private String desc;
	
	@Field("typeplat")
	@DBRef
	private TypePlat typeplat;
	
	private Date createdAt;
	
	
	public Menu() {
		this.createdAt=new Date();
	}
	
	@Override
	public String toString() {
		return String.format("Menu[id=%s,plat=%s,typeplat=%s,prix=%s]", id,plat,typeplat,prix);
	}
}
