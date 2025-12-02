package com.example.StreamFlow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "filmes")
public class Filme {

    @Id // Marca o campo como a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String diretor;
    private Integer duracaoMinutos;
    private String urlVideo;
    private String urlTrailer;
}