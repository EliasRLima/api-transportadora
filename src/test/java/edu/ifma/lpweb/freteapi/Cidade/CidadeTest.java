package edu.ifma.lpweb.freteapi.Cidade;

import edu.ifma.lpweb.freteapi.model.Cidade;
import edu.ifma.lpweb.freteapi.repository.CidadeRepository;
import edu.ifma.lpweb.freteapi.service.CidadeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import org.hamcrest.core.IsNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;


@DataJpaTest
public class CidadeTest {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeService cidadeService;

    @Test
    public void injectedComponentsAreNotNull(){
        assertThat(cidadeService, IsNull.notNullValue());
        assertThat(cidadeRepository, IsNull.notNullValue());
    }

    @Test
    public void verificaSalvaCidade(){
        List<Cidade> cidadesRetornoService = cidadeService.todos();
        List<Cidade> cidadesRetornoRepository = cidadeRepository.findAll();
        assertEquals(cidadesRetornoService.size(), cidadesRetornoRepository.size());

        cidadeService.salva(new Cidade(10, "Rosario", "MA", BigDecimal.valueOf(10.5)));
        Optional<Cidade> cidade = cidadeService.buscaPor(10);
        assertTrue(cidade.isPresent());

        cidadeRepository.save(new Cidade(11, "Rosario", "MA", BigDecimal.valueOf(10.5)));
        cidade = cidadeRepository.findById(11);
        assertTrue(cidade.isPresent());

        int idCidade = 1;
        Optional<Cidade> cidadeRetornoService = cidadeService.buscaPor(idCidade);
        Optional<Cidade> cidadeRetornoRepository = cidadeRepository.findById(idCidade);
        assertEquals(cidadeRetornoRepository.isPresent(), cidadeRetornoService.isPresent());
        assertEquals(cidadeRetornoRepository.get().getId(), cidadeRetornoService.get().getId());

    }

    @Test
    public void verificaBuscaCidade(){
        int idCidade = 1;
        Optional<Cidade> buscaRepository = cidadeRepository.findById(idCidade);
        Optional<Cidade> buscaService = cidadeService.buscaPor(idCidade);

        assertEquals(buscaRepository.isPresent(), buscaService.isPresent());
        assertEquals(buscaRepository.get().getId(), buscaService.get().getId());

        int idCidade2 = 2;
        buscaRepository = cidadeRepository.findById(idCidade2);
        assertTrue(buscaRepository.isPresent());
        assertNotEquals(buscaRepository.get().getId(), buscaService.get().getId());

        List<Cidade> cidadesRetornoService = cidadeService.todos();
        List<Cidade> cidadesRetornoRepository = cidadeRepository.findAll();
        assertFalse(cidadesRetornoService.isEmpty());
        assertEquals(cidadesRetornoService.size(), cidadesRetornoRepository.size());

    }

}
