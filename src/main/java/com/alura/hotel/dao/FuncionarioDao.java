package com.alura.hotel.dao;

import com.alura.hotel.modelo.Funcionario;
import java.util.List;
import javax.persistence.EntityManager;


public class FuncionarioDao {
    
    private EntityManager em;

    public FuncionarioDao(EntityManager em) {
        this.em = em;
    }
    
     public void guardar(Funcionario funcionario){
        this.em.persist(funcionario);
    }
    
    public void actualizar(Funcionario funcionario){
        this.em.merge(funcionario);
    }
    
    public void remover(Funcionario funcionario){
        funcionario = this.em.merge(funcionario);
        this.em.remove(funcionario);
    }
    
    public List<Funcionario> consultarTodos(){
        String jpql = "SELECT f FROM Funcionario AS f";
        return this.em.createQuery(jpql, Funcionario.class).getResultList();
    }
    
    public Funcionario login(String funcionario){
        String jpql = "SELECT f FROM Funcionario f WHERE f.funcionario = :funcionario";
        return this.em.createQuery(jpql, Funcionario.class).setParameter("funcionario", funcionario).getSingleResult();
    }
}
