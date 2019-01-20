package com.restau.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restau.app.models.Menu;
import com.restau.app.models.TypePlat;
import com.restau.app.repositories.MenuRepository;
import com.restau.app.repositories.TypePlatRepository;

/**
 * @since 24/12/2018
 * @author JSHARP
 * @version 1.0
 *
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuRepository menuRepository;

	@Override
	public List<Menu> findAllMenu() {
		return menuRepository.findAll();
	}

	@Override
	public Menu saveMenu(Menu menu) {
		return menuRepository.save(menu);
	}

	@Override
	public Menu modifyMenu(String id, Menu menu) {
		return menuRepository.findById(id).
				map(menuData->{
					menuData.setPlat(menu.getPlat());
					Menu menuupdate = menuRepository.save(menuData);
					return ResponseEntity.ok().body(menuupdate);
		}).orElse(ResponseEntity.notFound().build()).getBody();
	}

	@Override
	public Menu findMenu(String nom) {
		return menuRepository.findByPlat(nom);
	}
	@Override
	public Menu findById(String id) throws Exception {
			Optional<Menu> menu = menuRepository.findById(id);
			if(menu.isPresent())return menu.get();
			throw new Exception("Ce menu n'existe pas");
	}

	@Override
	public boolean deleteMenu(String id) {
		menuRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Menu> findByTypePlat(TypePlat typeplat) {
		return menuRepository.findByTypeplat(typeplat);
	}
}
