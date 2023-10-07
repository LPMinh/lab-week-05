package com.minh.labweek05.repositories;

import com.minh.labweek05.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}