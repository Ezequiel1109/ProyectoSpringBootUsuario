package com.example.demo.Services;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Models.TelefonoModel;
import com.example.demo.Models.UsuarioModel;
import com.example.demo.Repositories.UsuarioRepository;




@Service
public class UsuarioServices {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }
    public UsuarioModel crearUsuario(UsuarioModel usuarioM, String passwordRegex, String emailRegex) {
        validateUsuario(usuarioM, passwordRegex, emailRegex);

        usuarioM.setPassword(passwordEncoder.encode(usuarioM.getPassword()));
        usuarioM.setCreated(LocalDateTime.now());
        usuarioM.setModified(LocalDateTime.now());
        usuarioM.setLastLogin(LocalDateTime.now());
        usuarioM.setToken(UUID.randomUUID().toString());
        usuarioM.setActive(true);

        return usuarioRepository.save(usuarioM);
    }
    private void validateUsuario(UsuarioModel usuarioM, String passwordRegex, String emailRegex) {
        validatePassword(usuarioM.getPassword(), passwordRegex);
        validateEmail(usuarioM.getEmail(), emailRegex);

        UsuarioModel existingUsuario = usuarioRepository.findByEmail(usuarioM.getEmail());
        if (existingUsuario != null) {
            throw new RuntimeException("El correo ya registrado");
        }

        validatePhoneList(usuarioM.getPhones());
    }

    private void validatePassword(String password, String passwordRegex) {
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new RuntimeException("La clave no cumple con el formato requerido");
        }
    }

    private void validateEmail(String email, String emailRegex) {
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new RuntimeException("El correo no cumple con el formato requerido");
        }
    }

    private void validatePhoneList(List<TelefonoModel> phoneList) {
        for (TelefonoModel phone : phoneList) {
            if (phone.getNumber() == null || phone.getNumber().isEmpty()
                    || phone.getCitycode() == null || phone.getCitycode().isEmpty()
                    || phone.getContrycode() == null || phone.getContrycode().isEmpty()) {
                throw new RuntimeException("El formato de los teléfonos no es correcto");
            }
        }
    }

    
    /*@SuppressWarnings("null") es para guardar al usuario que es ingresado por Postman
    public UsuarioModel guardarUsuario(UsuarioModel usuario){
        return usuarioRepository.save(usuario);
    }*/
    @SuppressWarnings("null")
    public Optional<UsuarioModel> obtenerPorId(Long id){
        return usuarioRepository.findById(id);
    }

    public ArrayList<UsuarioModel> obtenerPorNombre(String name){
        return  usuarioRepository.findByNombre(name);
    }

    @SuppressWarnings("null")
    public boolean eliminarUsuario(Long id){
        try {
            usuarioRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}
