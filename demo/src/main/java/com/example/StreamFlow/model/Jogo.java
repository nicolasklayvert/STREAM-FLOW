package com.example.StreamFlow.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jogos")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String desenvolvedora;

    private String plataforma; // Ex: "PC", "PS5", ou "PC, PS5, Xbox"

    // Use columnDefinition = "TEXT" se o texto for muito longo (mais de 255 caracteres)
    @Column(name = "requisitos_sistema", columnDefinition = "TEXT")
    private String requisitosSistema;

    @Column(name = "url_download")
    private String urlDownload;

    @Column(name = "versao_atual")
    private String versaoAtual;
}
