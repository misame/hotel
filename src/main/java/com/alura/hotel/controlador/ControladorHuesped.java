package com.alura.hotel.controlador;

import com.alura.hotel.dao.HuespedDao;
import com.alura.hotel.dao.ReservaDao;
import com.alura.hotel.modelo.Huesped;
import com.alura.hotel.modelo.Reserva;
import com.alura.hotel.utils.JPAUtils;
import java.util.List;
import javax.persistence.EntityManager;

public class ControladorHuesped {

    private Huesped huesped;

    public ControladorHuesped() {
    }

    public ControladorHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public List<Huesped> obtenerHuespedes() {
        EntityManager em = JPAUtils.getEntityManager();
        HuespedDao hd = new HuespedDao(em);
        em.getTransaction().begin();
        List<Huesped> consultarTodos = hd.consultarTodos();
        em.getTransaction().commit();
        em.close();
        return consultarTodos;
    }

    public void actualizarHuesped(Huesped huesped) {
        EntityManager em = JPAUtils.getEntityManager();
        HuespedDao hd = new HuespedDao(em);
        em.getTransaction().begin();
        hd.actualizar(huesped);
        em.getTransaction().commit();
        em.close();
    }

    public boolean eliminarHuesped(Long id) {
        EntityManager em = JPAUtils.getEntityManager();
        HuespedDao hd = new HuespedDao(em);
        ReservaDao rd = new ReservaDao(em);
        em.getTransaction().begin();
        Huesped huesped = hd.consultaPorId(id);

        if (huesped != null) {
            for (Reserva reserva : huesped.getReservas()) {
            rd.remover(reserva);
            em.getTransaction().commit();            
            }
            em.close();
            return true;
        }else{
            em.close();
            return false;
        }
        
    }

    public void agregarHuesped(Huesped huesped) {
        EntityManager em = JPAUtils.getEntityManager();
        HuespedDao hd = new HuespedDao(em);
        em.getTransaction().begin();
        hd.guardar(huesped);
        em.getTransaction().commit();
        em.close();
    }
}
