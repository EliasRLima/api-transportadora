package com.ifma.api.servicos;

import com.ifma.api.entidades.Cidade;
import com.ifma.api.repositorios.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    @Transactional(readOnly = true)
    public Page<Cidade> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Cidade findById(Long id) {
        return repository.findById(id).get();
    }
}
