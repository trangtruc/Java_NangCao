package com.example;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.*;


public interface ContactRepository extends CrudRepository<Contact, Integer> {
	List<Contact> findByNameContaining(String q);
}
