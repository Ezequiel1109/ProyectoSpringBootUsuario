package com.example.demo.Controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.UsuarioModel;
import com.example.demo.Services.UsuarioServices;

@RestController
@RequestMapping("/usuario")
public class UsuarioControllers {
    @Autowired
    UsuarioServices usuarioServices;

    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return usuarioServices.obtenerUsuarios();
    }
    
    @PostMapping()
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario){
        return this.usuarioServices.crearUsuario(usuario, null, null);
    }

    @GetMapping(path = "/{id}")
    public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id")Long id){
        return this.usuarioServices.obtenerPorId(id);
    }

    @GetMapping(path = "/buscarpornombre")   
    public ArrayList<UsuarioModel> obtenerUsuarioPorNombre(@RequestParam("name") String name) { 
        return this.obtenerUsuarioPorNombre(name);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.usuarioServices.eliminarUsuario(id);
        if (ok) {
            return "Se elimino el usuario con el  id: " + id;
        }else{
            return "No se pudo eliminar el usuario con el id :" + id;
        }
    }
}
