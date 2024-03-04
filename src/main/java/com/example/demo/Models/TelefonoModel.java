package com.example.demo.Models;
import jakarta.persistence.*;

@Entity
@Table(name = "telefonos")
public class TelefonoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idTelefono;
    //@ManyToOne()
    //@JoinColumn(name="idPersona", referencedColumnName="idPersona")
    //private UsuarioModel usuario;
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private String citycode;
    @Column(nullable = false)
    private String contrycode;
    
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getCitycode() {
        return citycode;
    }
    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }
    public String getContrycode() {
        return contrycode;
    }
    public void setContrycode(String contrycode) {
        this.contrycode = contrycode;
    }
}