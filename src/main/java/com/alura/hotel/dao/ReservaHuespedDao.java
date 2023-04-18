package com.alura.hotel.dao;

import java.util.List;
import javax.persistence.EntityManager;

public class ReservaHuespedDao {

    private EntityManager em;

    public ReservaHuespedDao(EntityManager em) {

        this.em = em;
    }

    public List<Object[]> consultaHuespedesYReservaApellido(String apellido) {
        
        String jpql = "SELECT h, r FROM Huesped h JOIN h.reservas r WHERE h.apellido = :apellido";
        return em.createQuery(jpql, Object[].class)
                .setParameter("apellido", apellido)
                .getResultList();
    }

    public List<Object[]> consultaHuespedYReservaId(Long reservaId) {
        
        String jpql = "SELECT h, r FROM Huesped h JOIN h.reservas r WHERE r.id = :reservaId";
        return em.createQuery(jpql, Object[].class)
                .setParameter("reservaId", reservaId)
                .getResultList();
    }

}
