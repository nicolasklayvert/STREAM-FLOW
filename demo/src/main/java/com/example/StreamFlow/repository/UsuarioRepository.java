package com.example.StreamFlow.repository;

import com.example.StreamFlow.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional; 

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    
    Optional<Usuario> findByEmail(String email);

}