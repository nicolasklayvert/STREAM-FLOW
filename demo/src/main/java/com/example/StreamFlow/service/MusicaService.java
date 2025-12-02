package com.example.StreamFlow.service;

import com.example.StreamFlow.model.Musica;
import com.example.StreamFlow.repository.MusicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicaService {

    private final MusicaRepository musicaRepository;

    public MusicaService(MusicaRepository musicaRepository) {
        this.musicaRepository = musicaRepository;
    }

    public List<Musica> findAll() {
        return musicaRepository.findAll();
    }

    public Optional<Musica> findById(Long id) {
        return musicaRepository.findById(id);
    }

    public Musica save(Musica musica) {
        return musicaRepository.save(musica);
    }

    public void deleteById(Long id) {
        musicaRepository.deleteById(id);
    }
}
