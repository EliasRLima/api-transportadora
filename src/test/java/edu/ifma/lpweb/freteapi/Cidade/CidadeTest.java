package edu.ifma.lpweb.freteapi.Cidade;

import edu.ifma.lpweb.freteapi.model.Cidade;
import edu.ifma.lpweb.freteapi.repository.CidadeRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import org.hamcrest.core.IsNull;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@DataJpaTest
public class CidadeTest {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(cidadeRepository, IsNull.notNullValue());
    }

    @Test
    public void verificaNumeroCidadeSalvas() {
        cidadeRepository.save(new Cidade(1, "Rosario", "MA", BigDecimal.valueOf(10.5)));
        List<Cidade> users = (List<Cidade>) cidadeRepository.findAll();

        assertEquals(1, users.size());
    }



}
