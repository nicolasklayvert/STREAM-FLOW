package com.example.StreamFlow.service;

import com.example.StreamFlow.model.Serie;
import com.example.StreamFlow.repository.SerieRepository;
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
public class SerieTeste {

    @InjectMocks
    private SerieService serieService;

    @Mock
    private SerieRepository serieRepository;

    private Serie serie;

    @BeforeEach
    void setUp() {
        serie = Serie.builder()
                .id(1L)
                .criador("Vince Gilligan")
                .concluida(true)
                .build();
    }

    // 1. Salvar
    @Test
    void deveSalvarSerie() {
        Mockito.when(serieRepository.save(Mockito.any(Serie.class))).thenReturn(serie);
        Serie salva = serieService.save(serie);
        Assertions.assertNotNull(salva);
    }

    // 2. Buscar ID
    @Test
    void deveEncontrarSeriePorId() {
        Mockito.when(serieRepository.findById(1L)).thenReturn(Optional.of(serie));
        Optional<Serie> result = serieService.findById(1L);
        Assertions.assertTrue(result.isPresent());
    }

    // 3. Buscar ID inexistente
    @Test
    void naoDeveEncontrarSerieInexistente() {
        Mockito.when(serieRepository.findById(2L)).thenReturn(Optional.empty());
        Optional<Serie> result = serieService.findById(2L);
        Assertions.assertTrue(result.isEmpty());
    }

    // 4. Listar
    @Test
    void deveRetornarListaDeSeries() {
        Mockito.when(serieRepository.findAll()).thenReturn(Arrays.asList(serie));
        List<Serie> lista = serieService.findAll();
        Assertions.assertFalse(lista.isEmpty());
    }

    // 5. Listar Vazio
    @Test
    void deveRetornarListaVazia() {
        Mockito.when(serieRepository.findAll()).thenReturn(Collections.emptyList());
        List<Serie> lista = serieService.findAll();
        Assertions.assertTrue(lista.isEmpty());
    }

    // 6. Deletar
    @Test
    void deveDeletarSerie() {
        serieService.deleteById(1L);
        Mockito.verify(serieRepository, Mockito.times(1)).deleteById(1L);
    }

    // 7. Verificar status boolean
    @Test
    void deveVerificarSeSerieEstaConcluida() {
        Mockito.when(serieRepository.findById(1L)).thenReturn(Optional.of(serie));
        Optional<Serie> s = serieService.findById(1L);
        Assertions.assertTrue(s.get().isConcluida());
    }

    // 8. Verificar criador
    @Test
    void deveVerificarNomeDoCriador() {
        Mockito.when(serieRepository.save(serie)).thenReturn(serie);
        Serie s = serieService.save(serie);
        Assertions.assertEquals("Vince Gilligan", s.getCriador());
    }
}