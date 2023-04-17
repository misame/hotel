package com.alura.hotel.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "funcionarios")
public class Funcionario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String funcionario;
    
    @Column(nullable = false)
    private String pass;
    
    @Column(nullable = false)
    private Integer categoria;

    public Funcionario() {
    }

    public Funcionario(String nombre, String pass, Integer categoria) {
        this.funcionario = nombre;
        this.pass = pass;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String apellido) {
        this.pass = apellido;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }
    
    
}
