package com.restau.app.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.restau.app.models.Menu;
import com.restau.app.models.TypePlat;


@Repository
public interface MenuRepository  extends MongoRepository<Menu, String> {
	public Menu findByPlat(String nom);
	public List<Menu> findByTypeplat(TypePlat typeplat);
}
