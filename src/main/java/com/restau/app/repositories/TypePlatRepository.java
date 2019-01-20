package com.restau.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.restau.app.models.TypePlat;


@Repository
public interface TypePlatRepository  extends MongoRepository<TypePlat, String> {
	public TypePlat findByNom(String nom);
}
