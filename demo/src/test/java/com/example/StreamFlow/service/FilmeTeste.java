package com.example.StreamFlow.service;

import com.example.StreamFlow.model.Filme;
import com.example.StreamFlow.repository.FilmeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FilmeTeste {

    @InjectMocks
    private FilmeService filmeService;

    @Mock
    private FilmeRepository filmeRepository;

    private Filme filme;

    @BeforeEach
    void setUp() {
        filme = Filme.builder()
                .id(1L)
                .diretor("Zack Snyder")
                .duracaoMinutos(150)
                .urlVideo("http://video.com")
                .urlTrailer("http://trailer.com")
                .build();
    }

    // 1. Teste de Salvar
    @Test
    void deveSalvarFilmeComSucesso() {
        Mockito.when(filmeRepository.save(Mockito.any(Filme.class))).thenReturn(filme);
        Filme salvo = filmeService.save(filme);
        Assertions.assertEquals("Zack Snyder", salvo.getDiretor());
    }

    // 2. Teste de Buscar por ID (Sucesso)
    @Test
    void deveBuscarFilmePorIdExistente() {
        Mockito.when(filmeRepository.findById(1L)).thenReturn(Optional.of(filme));
        Optional<Filme> encontrado = filmeService.findById(1L);
        Assertions.assertTrue(encontrado.isPresent());
    }

    // 3. Teste de Buscar por ID (Falha)
    @Test
    void deveRetornarVazioAoBuscarIdInexistente() {
        Mockito.when(filmeRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<Filme> encontrado = filmeService.findById(99L);
        Assertions.assertFalse(encontrado.isPresent());
    }

    // 4. Teste de Listar Todos (Com dados)
    @Test
    void deveListarTodosFilmes() {
        Mockito.when(filmeRepository.findAll()).thenReturn(Arrays.asList(filme));
        List<Filme> lista = filmeService.findAll();
        Assertions.assertEquals(1, lista.size());
    }

    // 5. Teste de Listar Todos (Vazio)
    @Test
    void deveRetornarListaVaziaSeNaoHouverFilmes() {
        Mockito.when(filmeRepository.findAll()).thenReturn(Collections.emptyList());
        List<Filme> lista = filmeService.findAll();
        Assertions.assertTrue(lista.isEmpty());
    }

    // 6. Teste de Deletar
    @Test
    void deveChamarMetodoDeletarCorretamente() {
        Mockito.doNothing().when(filmeRepository).deleteById(1L);
        filmeService.deleteById(1L);
        Mockito.verify(filmeRepository, Mockito.times(1)).deleteById(1L);
    }

    // 7. Teste de Atualização (Simulado)
    @Test
    void deveAtualizarFilmeExistente() {
        filme.setDiretor("Christopher Nolan");
        Mockito.when(filmeRepository.save(filme)).thenReturn(filme);
        Filme atualizado = filmeService.save(filme);
        Assertions.assertEquals("Christopher Nolan", atualizado.getDiretor());
    }

    // 8. Teste de Integridade de Dados
    @Test
    void deveVerificarUrlDoTrailer() {
        Mockito.when(filmeRepository.findById(1L)).thenReturn(Optional.of(filme));
        Optional<Filme> encontrado = filmeService.findById(1L);
        Assertions.assertEquals("http://trailer.com", encontrado.get().getUrlTrailer());
    }
}