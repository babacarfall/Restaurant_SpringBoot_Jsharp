package com.restau.app.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.restau.app.models.Utilisateur;

@Repository
public interface UtilisateurRepository extends MongoRepository<Utilisateur, String> {
	public Utilisateur findByUsernameAndPassword(String username, String password);
	public Optional<Utilisateur> findByUsername(String username);
}
