package com.totalmed.svcbackend.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totalmed.svcbackend.domain.model.Vacina;
import com.totalmed.svcbackend.domain.repository.VacinaRepository;

@Service
public class VacinaService {

	@Autowired
	private VacinaRepository repository;

	public List<Vacina> buscarTodas() {
		return repository.findAll();
	}
}
