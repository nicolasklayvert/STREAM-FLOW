package com.example.StreamFlow.repository;

import com.example.StreamFlow.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

}
