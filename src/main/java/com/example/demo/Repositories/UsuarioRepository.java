package com.example.demo.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{
    public abstract ArrayList<UsuarioModel> findByNombre(String nombre);
    UsuarioModel findByEmail(String email);
}
