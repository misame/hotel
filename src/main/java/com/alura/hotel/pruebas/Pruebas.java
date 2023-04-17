
package com.alura.hotel.pruebas;

import com.alura.hotel.dao.FuncionarioDao;
import com.alura.hotel.dao.HuespedDao;
import com.alura.hotel.dao.ReservaDao;
import com.alura.hotel.modelo.Funcionario;
import com.alura.hotel.modelo.Huesped;
import com.alura.hotel.modelo.Reserva;
import com.alura.hotel.utils.JPAUtils;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;


public class Pruebas {
    
    public static void main(String[] args) {

       
    }
    
    public static void crearFuncionario(){
        
        EntityManager em = JPAUtils.getEntityManager();
        Funcionario funcionario = new Funcionario("admin", "admin", 1);
        FuncionarioDao funcionarioDao = new FuncionarioDao(em);
        
        em.getTransaction().begin();
        funcionarioDao.guardar(funcionario);
        em.getTransaction().commit();
        em.close();
    }
    
    public static void registroHuesped(){
        
        EntityManager em = JPAUtils.getEntityManager();
        HuespedDao huespedDao = new HuespedDao(em);
        Huesped huesped = new Huesped("Miguel Angel", "Sanchez Medrano", LocalDate.parse("1986-05-26"), "peru", "999999999");
        Huesped huesped2 = new Huesped("Humberto Alonso", "Sanchez Medrano", LocalDate.parse("1987-06-01"), "peru", "888888888");
        ReservaDao reservaDao = new ReservaDao(em);
        Reserva reserva = new Reserva(LocalDate.parse("2023-05-01"), LocalDate.parse("2023-06-01"), new BigDecimal("500"), "tarjeta", huesped);
        Reserva reserva2 = new Reserva(LocalDate.parse("2023-05-01"), LocalDate.parse("2023-07-01"), new BigDecimal("1000"), "efectivo", huesped2);

        em.getTransaction().begin();
        huespedDao.guardar(huesped);
        huespedDao.guardar(huesped2);
        reservaDao.guardar(reserva);
        reservaDao.guardar(reserva2);
        em.getTransaction().commit();
        em.close();     
    }

    public static void consultaReserva(){
        
        EntityManager em = JPAUtils.getEntityManager();
        HuespedDao huespedDao = new HuespedDao(em);
        ReservaDao reservaDao = new ReservaDao(em);
        
        em.getTransaction().begin();
        List<Reserva> consultarReservasPorHuesped = reservaDao.consultarReservasPorHuesped(2L);
        em.getTransaction().commit();
        em.close();
        System.out.println(consultarReservasPorHuesped);
        
    }
}
