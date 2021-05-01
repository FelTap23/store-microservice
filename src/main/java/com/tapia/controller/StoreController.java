package com.tapia.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tapia.model.Store;

@RestController("/")
public class StoreController {

	private final static List<Store> storeList = Arrays.asList(new Store("store-1", 1l), new Store("store-2", 2l),
			new Store("store-3", 3l));

	@GetMapping("/store")
	public List<Store> retrieveAllStores() {
		return storeList;
	}

	@GetMapping("/store/{id}")
	public ResponseEntity<Store> findById(@PathVariable long id) {

		final Comparator<Store> comparator = (s1, s2) -> (int) (s1.getStoreId() - s2.getStoreId());

		Collections.sort(storeList, comparator);

		int index = Collections.binarySearch(storeList, new Store("dummy", id), comparator);

		return index >= 0 ? new ResponseEntity<>(storeList.get(index), HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
