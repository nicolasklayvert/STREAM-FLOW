package com.example.StreamFlow.service;

import com.example.StreamFlow.model.Musica;
import com.example.StreamFlow.repository.MusicaRepository;
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
public class MusicaTeste {

    @InjectMocks
    private MusicaService musicaService;

    @Mock
    private MusicaRepository musicaRepository;

    private Musica musica;

    @BeforeEach
    void setUp() {
        musica = Musica.builder()
                .id(1L)
                .artista("Queen")
                .album("A Night at the Opera")
                .duracaoSegundos(354)
                .build();
    }

    // 1. Salvar
    @Test
    void deveSalvarMusica() {
        Mockito.when(musicaRepository.save(musica)).thenReturn(musica);
        Musica m = musicaService.save(musica);
        Assertions.assertEquals("Queen", m.getArtista());
    }

    // 2. Buscar ID
    @Test
    void deveBuscarMusicaPorId() {
        Mockito.when(musicaRepository.findById(1L)).thenReturn(Optional.of(musica));
        Optional<Musica> m = musicaService.findById(1L);
        Assertions.assertTrue(m.isPresent());
    }

    // 3. Buscar ID Inexistente
    @Test
    void deveRetornarVazioSeIdNaoExiste() {
        Mockito.when(musicaRepository.findById(50L)).thenReturn(Optional.empty());
        Optional<Musica> m = musicaService.findById(50L);
        Assertions.assertTrue(m.isEmpty());
    }

    // 4. Find All
    @Test
    void deveListarTodasMusicas() {
        Mockito.when(musicaRepository.findAll()).thenReturn(Arrays.asList(musica));
        List<Musica> lista = musicaService.findAll();
        Assertions.assertEquals(1, lista.size());
    }

    // 5. Find All Vazio
    @Test
    void deveRetornarListaVaziaDeMusicas() {
        Mockito.when(musicaRepository.findAll()).thenReturn(Collections.emptyList());
        List<Musica> lista = musicaService.findAll();
        Assertions.assertTrue(lista.isEmpty());
    }

    // 6. Delete
    @Test
    void deveDeletarMusica() {
        musicaService.deleteById(1L);
        Mockito.verify(musicaRepository, Mockito.times(1)).deleteById(1L);
    }

    // 7. Check Album
    @Test
    void deveVerificarNomeDoAlbum() {
        Mockito.when(musicaRepository.findById(1L)).thenReturn(Optional.of(musica));
        Optional<Musica> m = musicaService.findById(1L);
        Assertions.assertEquals("A Night at the Opera", m.get().getAlbum());
    }

    // 8. Check Duracao
    @Test
    void deveVerificarDuracao() {
        Mockito.when(musicaRepository.save(musica)).thenReturn(musica);
        Musica m = musicaService.save(musica);
        Assertions.assertEquals(354, m.getDuracaoSegundos());
    }
}