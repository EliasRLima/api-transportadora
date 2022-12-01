package com.ifma.api.servicos;

import com.ifma.api.entidades.Frete;
import com.ifma.api.repositorios.FreteRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class FreteService {

    @Autowired
    private FreteRepository repository;

    @Transactional(readOnly = true)
    public Page<Frete> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Frete findById(Long id) {
        return repository.findById(id).get();
    }
}
