package com.alura.hotel.dao;

import com.alura.hotel.modelo.Huesped;
import java.util.List;
import javax.persistence.EntityManager;

public class HuespedDao {
    private EntityManager em;

    public HuespedDao(EntityManager em) {
        this.em = em;
    }
    
    public void guardar(Huesped huesped){
        this.em.persist(huesped);
    }
    
    public void actualizar(Huesped huesped){
        this.em.merge(huesped);
    }
    
    public void remover(Huesped huesped){
        huesped = this.em.merge(huesped);
        this.em.remove(huesped);
    }
    
    public Huesped consultaPorId(Long id){
        return em.find(Huesped.class, id);
    }
    
    public List<Huesped> consultarTodos(){
        String jpql = "SELECT H FROM Huesped AS H";
        return em.createQuery(jpql, Huesped.class).getResultList();
    }
    
    public List<Huesped> consultaPorNombres(String nombre, String apellido){
        String jpql = "SELECT H FROM Huesped AS H WHERE H.nombre=:nombre AND H.apellido=:apellido";
        return em.createQuery(jpql, Huesped.class)
                .setParameter("nombre", nombre)
                .setParameter("apellido", apellido)
                .getResultList();
    }
}
