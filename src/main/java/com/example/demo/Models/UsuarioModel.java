package com.example.demo.Models;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
  
    //@Column(columnDefinition = "json", nullable = false)
    @ElementCollection 
    private List<TelefonoModel> phones; 

    @Column(nullable = true)   
    private LocalDateTime created;

    @Column(nullable = true)
    private LocalDateTime modified;

    @Column(nullable = true)
    private LocalDateTime lastLogin;

    @Column(nullable = true)
    private String token;

    @Column(nullable = false)
    private boolean isActive;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return nombre;
    }
    public void setName(String name) {
        this.nombre = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<TelefonoModel> getPhones() {
        return phones;
    }
    public void setPhones(List<TelefonoModel> phones) {
        this.phones = phones;
    }
    public LocalDateTime getCreated() {
        return created;
    }
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
    public LocalDateTime getModified() {
        return modified;
    }
    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    
}
