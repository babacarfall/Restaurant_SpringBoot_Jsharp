package com.restau.app.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.restau.app.models.Utilisateur;
import com.restau.app.repositories.UtilisateurRepository;

/**
 * @since 24/12/2018
 * @author JSHARP
 * @version 1.0
 *
 */
@Service
public class UtilisateurServiceImpl implements UtilisateurService  {

	@Autowired
	private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository userRepository) {
        this.utilisateurRepository = userRepository;
    }
	@Override
	public List<Utilisateur> findAllUtilisateur() {
		return utilisateurRepository.findAll();
	}

	@Override
	public Utilisateur saveUtilisateur(Utilisateur utilisateur) throws NoSuchAlgorithmException {
		utilisateur.setPassword(hassagePassWord(utilisateur.getPassword()));
		return utilisateurRepository.save(utilisateur);
	}

	@Override
	public Utilisateur modifyUtilisateur(String id, Utilisateur utilisateur) throws NoSuchAlgorithmException {
		if(utilisateur.getPassword()!=null)
			utilisateur.setPassword(hassagePassWord(utilisateur.getPassword()));
		return utilisateurRepository.findById(id).map(utilisateurData -> {
			utilisateurData.setUsername(utilisateur.getUsername());
			utilisateurData.setPassword(utilisateur.getPassword());
			Utilisateur utilisateurupdate = utilisateurRepository.save(utilisateurData);
			return ResponseEntity.ok().body(utilisateurupdate);
		}).orElse(ResponseEntity.notFound().build()).getBody();
	}

	@Override
	public boolean deleteUtilisateur(String id) {
		utilisateurRepository.deleteById(id);
		return true;
	}

	@Override
	public Utilisateur findByUsernameAndPassword(String username, String password) throws NoSuchAlgorithmException {
		password=hassagePassWord(password);
		return utilisateurRepository.findByUsernameAndPassword(username, password);
	}
	
	private String hassagePassWord(String password) throws NoSuchAlgorithmException {
	        MessageDigest md;
				md = MessageDigest.getInstance("MD5");
	        md.update(password.getBytes());
	        byte byteData[] = md.digest();
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
			return sb.toString();
	}
}
