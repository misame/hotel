package com.alura.hotel.dao;

import com.alura.hotel.modelo.Reserva;
import java.util.List;
import javax.persistence.EntityManager;

public class ReservaDao {

    private EntityManager em;


    public ReservaDao(EntityManager em) {
        this.em = em;
    }

    public void guardar(Reserva reserva) {
        this.em.persist(reserva);
    }

    public void actualizar(Reserva reserva) {
        this.em.merge(reserva);
    }

    public void remover(Reserva reserva) {
        reserva = this.em.merge(reserva);
        this.em.remove(reserva);
    }
    
    

    public List<Reserva> consultarReservasPorHuesped(Long huespedId) {
        String jpql="SELECT r FROM Huesped h JOIN h.reservas r WHERE h.id = :huespedId";
        return em.createQuery(jpql, Reserva.class).setParameter("huespedId", huespedId).getResultList();
    }
       
    public Long ultimoRegistro(){
        String jpql = "SELECT MAX(r.id) FROM Reserva r";
        Long id = em.createQuery(jpql, Long.class).getSingleResult();       
        if (id == null) {
            return 0L;
        }else{
            return id;
        }
    }

    public Reserva consultaPorId(Long id) {
        return em.find(Reserva.class, id);
    }

}
