package com.example.StreamFlow.service;

import com.example.StreamFlow.model.Filme;
import com.example.StreamFlow.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public List<Filme> findAll() {
        return filmeRepository.findAll();
    }

    public Optional<Filme> findById(Long id) {
        return filmeRepository.findById(id);
    }

    public Filme save(Filme filme) {
        return filmeRepository.save(filme);
    }

    public void deleteById(Long id) {
        filmeRepository.deleteById(id);
    }
}
