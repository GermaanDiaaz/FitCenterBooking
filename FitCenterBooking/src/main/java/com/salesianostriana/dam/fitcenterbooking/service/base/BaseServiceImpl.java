package com.salesianostriana.dam.fitcenterbooking.service.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseServiceImpl <T, ID, R extends JpaRepository<T, ID>> implements BaseService<T, ID>{

	@Autowired
	protected R repository;

	
	public List<T> findAll() {
		return repository.findAll();
	}

	public Optional<T> findById(ID id) {
		return repository.findById(id);
	}

	public T save(T t) {
		return repository.save(t);
	}

	public T edit(T t) {
		return repository.save(t);
	}

	public void delete(T t) {
		repository.delete(t);

	}

	public void deleteById(ID id) {
		repository.deleteById(id);
	}
}
