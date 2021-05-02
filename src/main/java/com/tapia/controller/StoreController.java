package com.tapia.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tapia.model.Store;
import com.tapia.repository.StoreRepository;

@RestController("/")
public class StoreController {

	@Autowired
	private StoreRepository storeRepository;
	
	@PostConstruct
	private void loadDummyData() {
		
		
		storeRepository.save(new Store(null, "Golde chicken"));
		storeRepository.save(new Store(null, "Burger Boy"));
		storeRepository.save(new Store(null, "MC Coapan"));
		
	}
	

	@GetMapping("/store")
	public List<Store> retrieveAllStores() {
		return storeRepository.findAll();
	}

	@GetMapping("/store/{id}")
	public ResponseEntity<Store> findById(@PathVariable long id) {

		Optional<Store> response = storeRepository.findById(id);
		return response.isPresent() ? new ResponseEntity<>(response.get(), HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
