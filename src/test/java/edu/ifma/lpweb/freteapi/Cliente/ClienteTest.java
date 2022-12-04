package edu.ifma.lpweb.freteapi.Cliente;

import edu.ifma.lpweb.freteapi.model.Cliente;
import edu.ifma.lpweb.freteapi.repository.ClienteRepository;
import edu.ifma.lpweb.freteapi.service.ClienteService;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

@DataJpaTest
public class ClienteTest {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ClienteService service;

    @Test
    public void garantirComponentesNaoNulos(){
        assertThat(service, IsNull.notNullValue());
        assertThat(repository, IsNull.notNullValue());
    }

    @Test
    public void verificaSalvarCliente(){
        List<Cliente> buscaAntesSalvar = service.todos();

        Cliente salvo = service.salva(new Cliente(10, "Antonio", "rua 1", "0000-0000"));
        assertTrue(Objects.nonNull(salvo));
        Optional<Cliente> busca = service.buscaPor(10);
        assertTrue(busca.isPresent());

        List<Cliente> buscaDepoisSalvar = service.todos();

        assertTrue(buscaAntesSalvar.size() < buscaDepoisSalvar.size());

    }

    @Test
    public void verificaBuscaCliente(){

        List<Cliente> buscaTodos = service.todos();
        List<Cliente> buscaTodosRep = repository.findAll();
        assertFalse(buscaTodos.isEmpty());
        assertFalse(buscaTodosRep.isEmpty());
        assertEquals(buscaTodos.size(), buscaTodosRep.size());

        int idCliente = buscaTodos.get(0).getId();
        Optional<Cliente> buscaUnico = service.buscaPor(idCliente);
        assertTrue(buscaUnico.isPresent());
        assertEquals(buscaTodos.get(0).getNome(), buscaUnico.get().getNome());

        Optional<Cliente> buscaUnicoRep = repository.findById(idCliente);
        assertTrue(buscaUnicoRep.isPresent());
        assertEquals(buscaUnicoRep.get().getNome(), buscaUnico.get().getNome());

    }

    @Test
    public void verificaRemoverCliente(){
        
        int idCliente = 1;
        Optional<Cliente> buscaCliente = service.buscaPor(idCliente);
        assertTrue(buscaCliente.isPresent());
        service.removePelo(idCliente);
        Optional<Cliente> buscaClienteDeletado = service.buscaPor(idCliente);
        assertFalse(buscaClienteDeletado.isPresent());

        idCliente = 2;
        Optional<Cliente> buscaClienteRep = repository.findById(idCliente);
        assertTrue(buscaClienteRep.isPresent());
        repository.deleteById(idCliente);
        Optional<Cliente> buscaClienteRepDeletado = repository.findById(idCliente);
        assertFalse(buscaClienteRepDeletado.isPresent());

    }

}
