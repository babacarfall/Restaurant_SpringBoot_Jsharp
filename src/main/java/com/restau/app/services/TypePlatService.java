package com.restau.app.services;

import java.util.List;

import com.restau.app.models.TypePlat;

public interface TypePlatService {

	public List<TypePlat> findAllTypePlat();
	public TypePlat saveTypePlat(TypePlat typeplat);
	public TypePlat modifyTypePlat(String id,TypePlat typeplat);
	public TypePlat findTypePlat(String nom);
	public boolean deleteTypePlat(String id);	
}
