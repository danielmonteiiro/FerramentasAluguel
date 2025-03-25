/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.aluguel.ferramentasaluguel.dao;

import br.com.aluguel.ferramentasaluguel.model.Ferramentas;
import br.com.aluguel.ferramentasaluguel.persistencia.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author danie
 */
public class FerramentaDAO {

    /**
     * Adiciona uma nova ferramenta ao banco de dados.
     *
     * @param f Objeto Ferramentas a ser cadastrado.
     */
    public void cadastrarFerramenta(Ferramentas f) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    /**
     * Exclui uma ferramenta com base no ID.
     *
     * @param f Objeto Ferramentas a ser excluído.
     */
    public void excluirFerramenta(Ferramentas f) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(f) ? f : em.merge(f));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    /**
     * Busca uma ferramenta no banco de dados pelo seu ID.
     *
     * @param id O ID da ferramenta a ser buscada.
     * @return O objeto Ferramentas correspondente ao ID, ou null se não for
     * encontrado.
     */
    public Ferramentas buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Ferramentas.class, id);
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    /**
     * Lista todas as ferramentas cadastradas no banco de dados, com opção de
     * filtrar por nome.
     *
     * @param nome String usada para filtrar as ferramentas pelo nome. Se vazia,
     * retorna todas as ferramentas.
     * @return Uma lista de objetos Ferramentas que correspondem ao filtro (ou
     * todos, se nenhum filtro for aplicado).
     */
    public List<Ferramentas> listar(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String textoQuery = "SELECT f FROM Ferramentas f";
            if (!nome.isEmpty()) {
                textoQuery += " WHERE f.nome LIKE :nome";
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

    /**
     * Retorna o nome e o preço de uma ferramenta pelo nome.
     *
     * @param nome O nome da ferramenta a ser buscada.
     * @return Lista de objetos com nome e preço das ferramentas encontradas.
     */
    public List<Object[]> buscarNomePreco(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String textoQuery = "SELECT f.nome, f.preco_diaria FROM Ferramentas f WHERE f.nome LIKE :nome";
            Query consulta = em.createQuery(textoQuery);
            consulta.setParameter("nome", "%" + nome + "%");
            return consulta.getResultList();
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
    
    
    /**
     * Atualiza as informações de uma ferramentas do banco de dados,
     *
     * @param f O objeto ferramenta com as informações atualizadas.
     */
    public void atualizarFerramenta(Ferramentas f) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(f);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
    
    
    public Ferramentas buscarPorNome(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT f FROM Ferramentas f WHERE f.nome = :nome", Ferramentas.class)
                    .setParameter("nome", nome)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Ferramentas salvar(Ferramentas ferramenta) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        ferramenta = em.merge(ferramenta); // Salva e retorna a ferramenta com ID gerado
        em.getTransaction().commit();
        em.close();
        return ferramenta;
    }

}
