package com.example.StreamFlow.service;

import com.example.StreamFlow.model.Usuario;
import com.example.StreamFlow.repository.UsuarioRepository;
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
public class UsuarioTeste {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Nicolas");
        usuario.setEmail("nicolas@teste.com");
        usuario.setSenhaHash("12345");
    }

    // TESTE 1: Salvar com sucesso
    @Test
    void deveSalvarUsuarioComSucesso() {
        Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
        Usuario salvo = usuarioService.save(usuario);
        Assertions.assertEquals("Nicolas", salvo.getNome());
    }

    // TESTE 2: Buscar por ID existente
    @Test
    void deveBuscarUsuarioPorIdExistente() {
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Optional<Usuario> encontrado = usuarioService.findById(1L);
        Assertions.assertTrue(encontrado.isPresent());
        Assertions.assertEquals(1L, encontrado.get().getId());
    }

    // TESTE 3: Buscar por ID inexistente
    @Test
    void deveRetornarVazioAoBuscarIdInexistente() {
        Mockito.when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<Usuario> encontrado = usuarioService.findById(99L);
        Assertions.assertFalse(encontrado.isPresent());
    }

    // TESTE 4: Buscar por Email existente
    @Test
    void deveBuscarUsuarioPorEmail() {
        Mockito.when(usuarioRepository.findByEmail("nicolas@teste.com")).thenReturn(Optional.of(usuario));
        Optional<Usuario> encontrado = usuarioService.findByEmail("nicolas@teste.com");
        Assertions.assertTrue(encontrado.isPresent());
    }

    // TESTE 5: Buscar por Email inexistente
    @Test
    void deveRetornarVazioBuscarEmailInexistente() {
        Mockito.when(usuarioRepository.findByEmail("naoexiste@teste.com")).thenReturn(Optional.empty());
        Optional<Usuario> encontrado = usuarioService.findByEmail("naoexiste@teste.com");
        Assertions.assertFalse(encontrado.isPresent());
    }

    // TESTE 6: Listar todos com dados
    @Test
    void deveListarTodosUsuarios() {
        Mockito.when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario));
        List<Usuario> lista = usuarioService.findAll();
        Assertions.assertFalse(lista.isEmpty());
        Assertions.assertEquals(1, lista.size());
    }

    // TESTE 7: Listar todos vazios
    @Test
    void deveRetornarListaVaziaSeNaoHouverUsuarios() {
        Mockito.when(usuarioRepository.findAll()).thenReturn(Collections.emptyList());
        List<Usuario> lista = usuarioService.findAll();
        Assertions.assertTrue(lista.isEmpty());
    }

    // TESTE 8: Deletar
    @Test
    void deveChamarDeletarPeloId() {
        Mockito.doNothing().when(usuarioRepository).deleteById(1L);
        usuarioService.deleteById(1L);
        Mockito.verify(usuarioRepository, Mockito.times(1)).deleteById(1L);
    }
}