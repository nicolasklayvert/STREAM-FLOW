package com.example.StreamFlow.service;

import com.example.StreamFlow.model.Serie;
import com.example.StreamFlow.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieService {

    private final SerieRepository serieRepository;

    public SerieService(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public List<Serie> findAll() {
        return serieRepository.findAll();
    }

    public Optional<Serie> findById(Long id) {
        return serieRepository.findById(id);
    }

    public Serie save(Serie serie) {
        return serieRepository.save(serie);
    }

    public void deleteById(Long id) {
        serieRepository.deleteById(id);
    }
}
