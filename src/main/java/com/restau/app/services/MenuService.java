package com.restau.app.services;

import java.util.List;

import com.restau.app.models.Menu;
import com.restau.app.models.TypePlat;

public interface MenuService {

	public List<Menu> findAllMenu();
	public Menu saveMenu(Menu typeplat);
	public Menu modifyMenu(String id,Menu typeplat);
	public List<Menu>findByTypePlat(TypePlat typeplat);
	public Menu findMenu(String nom);
	public Menu findById(String id) throws Exception ;
	public boolean deleteMenu(String id);	
}
