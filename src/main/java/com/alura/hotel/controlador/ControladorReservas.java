package com.alura.hotel.controlador;

import com.alura.hotel.dao.HuespedDao;
import com.alura.hotel.dao.ReservaDao;
import com.alura.hotel.dao.ReservaHuespedDao;
import com.alura.hotel.modelo.Huesped;
import com.alura.hotel.modelo.Reserva;
import com.alura.hotel.utils.JPAUtils;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControladorReservas {

    private Huesped huesped;
    private Reserva reserva;

    public ControladorReservas() {
    }

    public ControladorReservas(Huesped huesped, Reserva reserva) {

        this.huesped = huesped;
        this.reserva = reserva;
    }

    public void registro() {

        EntityManager em = JPAUtils.getEntityManager();
        ReservaDao reservaDao = new ReservaDao(em);
        HuespedDao huespedDao = new HuespedDao(em);
        em.getTransaction().begin();
        huespedDao.guardar(huesped);
        reservaDao.guardar(reserva);
        em.getTransaction().commit();
        em.close();

    }

    public Long ultimoRegistro() {
        EntityManager em = JPAUtils.getEntityManager();
        ReservaDao reservaDao = new ReservaDao(em);
        em.getTransaction().begin();
        Long ur = reservaDao.ultimoRegistro();
        em.getTransaction().commit();
        em.close();
        return ur;
    }

    public List<Object[]> consultaHuespedYReservaId(Long reservaId) {
        EntityManager em = JPAUtils.getEntityManager();
        ReservaHuespedDao rhd = new ReservaHuespedDao(em);
        em.getTransaction().begin();
        List<Object[]> consultaHuespedYReservaId = rhd.consultaHuespedYReservaId(reservaId);
        em.getTransaction().commit();
        em.close();
        return consultaHuespedYReservaId;
    }

    public void ActualizarReservasHuespedes(JTable tbHuespedes, JTable tbReservas) {

        EntityManager em = JPAUtils.getEntityManager();
        HuespedDao hd = new HuespedDao(em);
        ReservaDao rd = new ReservaDao(em);

        // Recuperar la lista de hu�spedes de la tabla tbHuespedes
        List<Huesped> huespedes = new ArrayList<>();
        DefaultTableModel modeloHuespedes = (DefaultTableModel) tbHuespedes.getModel();
        for (int i = 0; i < modeloHuespedes.getRowCount(); i++) {
            Long id = Long.parseLong(modeloHuespedes.getValueAt(i, 0).toString());
            String nombre = modeloHuespedes.getValueAt(i, 1).toString();
            String apellido = modeloHuespedes.getValueAt(i, 2).toString();
            LocalDate fechaNacimiento = LocalDate.parse(modeloHuespedes.getValueAt(i, 3).toString());
            String nacionalidad = modeloHuespedes.getValueAt(i, 4).toString();
            String telefono = (String) modeloHuespedes.getValueAt(i, 5);
            System.out.println(nombre + " " + apellido + " " + fechaNacimiento + " " + nacionalidad + " " + telefono);
            huespedes.add(new Huesped(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono));
        }

        // Recuperar la lista de reservas de la tabla tbReservas
        List<Reserva> reservas = new ArrayList<>();
        DefaultTableModel modeloReservas = (DefaultTableModel) tbReservas.getModel();
        for (int i = 0; i < modeloReservas.getRowCount(); i++) {
            Long id = Long.parseLong(modeloReservas.getValueAt(i, 0).toString());
            LocalDate fechaEntrada = LocalDate.parse(modeloReservas.getValueAt(i, 1).toString());
            LocalDate fechaSalida = LocalDate.parse(modeloReservas.getValueAt(i, 2).toString());
            BigDecimal valor = BigDecimal.valueOf(Double.parseDouble(modeloReservas.getValueAt(i, 3).toString()));
            String formaPago = modeloReservas.getValueAt(i, 4).toString();
            Long huespedId = Long.parseLong(modeloReservas.getValueAt(i, 5).toString());
            Huesped huesped = hd.consultaPorId(huespedId);
            reservas.add(new Reserva(id, fechaEntrada, fechaSalida, valor, formaPago, huesped));
        }

        // Actualizar cada reserva con sus datos correspondientes
        for (Reserva reserva : reservas) {
            em.getTransaction().begin();
            rd.actualizar(reserva);
            em.getTransaction().commit();
        }

        // Actualizar cada hu�sped con sus datos correspondientes
        for (Huesped huesped : huespedes) {
            em.getTransaction().begin();
            hd.actualizar(huesped);
            em.getTransaction().commit();
        }
        em.close();
    }

    public boolean eliminarReserva(Long id) {

        EntityManager em = JPAUtils.getEntityManager();
        ReservaDao rd = new ReservaDao(em);

        em.getTransaction().begin();
        Reserva reserva = rd.consultaPorId(id);
        
        if(reserva != null){
            rd.remover(reserva);
            em.getTransaction().commit();
            em.close();
            return true;
        }else{
            em.close();
            return false;
        }
        
        
    }

}
