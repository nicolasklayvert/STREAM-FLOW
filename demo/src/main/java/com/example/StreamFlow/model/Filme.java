package com.example.StreamFlow.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Filme {

    private Long id;
    private String diretor;
    private Integer duracaoMinutos;
    private String urlVideo;
    private String urlTrailer;
}