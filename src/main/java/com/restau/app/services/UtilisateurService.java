package com.restau.app.services;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.restau.app.models.Utilisateur;

public interface UtilisateurService {

	public List<Utilisateur> findAllUtilisateur();
	public Utilisateur saveUtilisateur(Utilisateur utilisateur) throws NoSuchAlgorithmException;
	public Utilisateur modifyUtilisateur(String id,Utilisateur utilisateur)throws NoSuchAlgorithmException;
	public Utilisateur findByUsernameAndPassword(String username,String password)throws NoSuchAlgorithmException;
	public boolean deleteUtilisateur(String id);	
}
