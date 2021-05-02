package com.tapia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.tapia.model.Store;

@Repository
@Component
public interface StoreRepository  extends JpaRepository<Store, Long>{

}
