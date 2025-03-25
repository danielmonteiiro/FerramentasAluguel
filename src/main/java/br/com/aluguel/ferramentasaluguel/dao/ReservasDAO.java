/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.aluguel.ferramentasaluguel.dao;

import br.com.aluguel.ferramentasaluguel.model.Reservas;
import br.com.aluguel.ferramentasaluguel.persistencia.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author danie
 */
public class ReservasDAO {
    
    
    public void cadastrarReserva(Reservas reserva) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(reserva);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
    
    public List<Reservas> listarTodas() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String textoQuery = "SELECT r FROM Reservas r";
            TypedQuery<Reservas> consulta = em.createQuery(textoQuery, Reservas.class);
            return consulta.getResultList();
        } finally {
            em.close(); // Fecha o EntityManager
        }
    }



}
