/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.aluguel.ferramentasaluguel.dao;

import br.com.aluguel.ferramentasaluguel.model.Clientes;
import br.com.aluguel.ferramentasaluguel.persistencia.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author danie
 */
public class ClienteDAO {


    /**
     * @param  c Adiciona um novo cliente à lista.
     */
    public void  cadastrarCliente(Clientes c) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
    
  /**
     * Exclui clientes com base no nome.
     *
     * @param c do cliente a ser excluído.
     */
    public void excluirClientes(Clientes c) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(c) ? c: em.merge(c));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
    
    
        /**
     * Busca um podcast no banco de dados pelo seu ID.
     *
     * @param id O ID do cliente a ser buscado
     * @return O objeto clientecorrespondente ao ID, ou null se não for encontrado.
     */
    public Clientes buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Clientes.class, id);
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

 /**
     * Lista todos os podcasts cadastrados no banco de dados, com opção de filtrar por produtor.
     *
     * @param nome String usada para filtrar os clientes pelo nome .
     *                      Se vazia, retorna todos os cleintes.
     * @return Uma lista de objetos Clientes que correspondem ao filtro (ou todos, se nenhum filtro for aplicado).
     */
    public List<Clientes> listar(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String textoQuery = "SELECT c FROM Clientes c";
            if (!nome.isEmpty()) {
                textoQuery += " WHERE c.nome LIKE :nome";
            }
            Query consulta = em.createQuery(textoQuery);
            if (!nome.isEmpty()) {
                consulta.setParameter("nome", "%" + nome + "%");
            }
            return consulta.getResultList();
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
    
    public Integer getIdClientePorCPF(String cpf) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c.id FROM Clientes c WHERE c.cpf = :cpf", Integer.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // Retorna null se o CPF não for encontrado
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    
    /**
     * Atualiza as informações de um cliente no banco de dados.
     *
     * @param c O objeto Clientes com as informações atualizadas.
     */
    public void atualizarClientes(Clientes c) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

}
