package com.restau.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restau.app.models.TypePlat;
import com.restau.app.repositories.TypePlatRepository;

/**
 * @since 24/12/2018
 * @author JSHARP
 * @version 1.0
 *
 */
@Service
@Transactional
public class TypePlatServiceImpl implements TypePlatService {

	@Autowired
	TypePlatRepository typeplatRepository;

	@Override
	public List<TypePlat> findAllTypePlat() {
		return typeplatRepository.findAll();
	}

	@Override
	public TypePlat saveTypePlat(TypePlat typeplat) {
		return typeplatRepository.save(typeplat);
	}

	@Override
	public TypePlat modifyTypePlat(String id, TypePlat typeplat) {
		return typeplatRepository.findById(id).
				map(typeplatData->{
					typeplatData.setNom(typeplat.getNom());
					TypePlat typeplatupdate = typeplatRepository.save(typeplatData);
					return ResponseEntity.ok().body(typeplatupdate);
		}).orElse(ResponseEntity.notFound().build()).getBody();
	}

	@Override
	public TypePlat findTypePlat(String nom) {
		return typeplatRepository.findByNom(nom);
	}

	@Override
	public boolean deleteTypePlat(String id) {
		typeplatRepository.deleteById(id);
		return true;
	}
}
