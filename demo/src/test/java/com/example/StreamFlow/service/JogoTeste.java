package com.example.StreamFlow.service;

import com.example.StreamFlow.model.Jogo;
import com.example.StreamFlow.repository.JogoRepository;
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
public class JogoTeste {

    @InjectMocks
    private JogoService jogoService;

    @Mock
    private JogoRepository jogoRepository;

    private Jogo jogo;

    @BeforeEach
    void setUp() {
        jogo = Jogo.builder()
                .id(1L)
                .desenvolvedora("Nintendo")
                .plataforma("Switch")
                .versaoAtual("1.0")
                .build();
    }

    // 1. Salvar
    @Test
    void deveSalvarJogo() {
        Mockito.when(jogoRepository.save(jogo)).thenReturn(jogo);
        Jogo salvo = jogoService.save(jogo);
        Assertions.assertEquals("Nintendo", salvo.getDesenvolvedora());
    }

    // 2. Buscar ID
    @Test
    void deveBuscarJogoPorId() {
        Mockito.when(jogoRepository.findById(1L)).thenReturn(Optional.of(jogo));
        Optional<Jogo> result = jogoService.findById(1L);
        Assertions.assertTrue(result.isPresent());
    }

    // 3. Buscar ID Fail
    @Test
    void deveFalharAoBuscarJogoInexistente() {
        Mockito.when(jogoRepository.findById(10L)).thenReturn(Optional.empty());
        Optional<Jogo> result = jogoService.findById(10L);
        Assertions.assertTrue(result.isEmpty());
    }

    // 4. Listar
    @Test
    void deveListarJogos() {
        Mockito.when(jogoRepository.findAll()).thenReturn(Arrays.asList(jogo));
        List<Jogo> list = jogoService.findAll();
        Assertions.assertEquals(1, list.size());
    }

    // 5. Listar Vazio
    @Test
    void deveListarVazio() {
        Mockito.when(jogoRepository.findAll()).thenReturn(Collections.emptyList());
        List<Jogo> list = jogoService.findAll();
        Assertions.assertTrue(list.isEmpty());
    }

    // 6. Delete
    @Test
    void deveDeletarJogo() {
        jogoService.deleteById(1L);
        Mockito.verify(jogoRepository, Mockito.times(1)).deleteById(1L);
    }

    // 7. Verificar Plataforma
    @Test
    void deveVerificarPlataforma() {
        Mockito.when(jogoRepository.findById(1L)).thenReturn(Optional.of(jogo));
        Optional<Jogo> j = jogoService.findById(1L);
        Assertions.assertEquals("Switch", j.get().getPlataforma());
    }

    // 8. Teste de Update
    @Test
    void deveAtualizarVersao() {
        jogo.setVersaoAtual("2.0");
        Mockito.when(jogoRepository.save(jogo)).thenReturn(jogo);
        Jogo j = jogoService.save(jogo);
        Assertions.assertEquals("2.0", j.getVersaoAtual());
    }
}