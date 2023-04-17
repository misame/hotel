package com.alura.hotel.dao;

import com.alura.hotel.modelo.ReservaHuesped;
import java.util.List;
import javax.persistence.EntityManager;

public class ReservaHuespedDao {

    private EntityManager em;

    public ReservaHuespedDao(EntityManager em) {

        this.em = em;
    }

    public void guardar(ReservaHuesped reservaHuesped) {
        this.em.persist(reservaHuesped);
    }

    public void update(ReservaHuesped reservaHuesped) {
        ReservaHuesped existingReservaHuesped = em.find(ReservaHuesped.class, reservaHuesped.getId());

        if (existingReservaHuesped != null) {
            existingReservaHuesped.setHuesped(reservaHuesped.getHuesped());
            existingReservaHuesped.setFechaEntrada(reservaHuesped.getFechaEntrada());
            existingReservaHuesped.setFechaSalida(reservaHuesped.getFechaSalida());
            existingReservaHuesped.setValor(reservaHuesped.getValor());
            existingReservaHuesped.setFormaPago(reservaHuesped.getFormaPago());

            this.em.merge(existingReservaHuesped);
        }
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
