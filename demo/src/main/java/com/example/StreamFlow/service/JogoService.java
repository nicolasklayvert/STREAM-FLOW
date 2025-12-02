package com.example.StreamFlow.service;

import com.example.StreamFlow.model.Jogo;
import com.example.StreamFlow.repository.JogoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogoService {

    private final JogoRepository jogoRepository;

    public JogoService(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }

    public List<Jogo> findAll() {
        return jogoRepository.findAll();
    }

    public Optional<Jogo> findById(Long id) {
        return jogoRepository.findById(id);
    }

    public Jogo save(Jogo jogo) {
        return jogoRepository.save(jogo);
    }

    public void deleteById(Long id) {
        jogoRepository.deleteById(id);
    }
}
