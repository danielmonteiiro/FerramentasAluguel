/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.aluguel.ferramentasaluguel.persistencia;

/**
 *
 * @author danie
 */
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Esta classe fornece utilitários para a configuração e gerenciamento do EntityManager
 * para a unidade de persistência especificada.
 */
public class JPAUtil {
    // constante para centralizar o nome da unidade de persistência
    // se o nome mudar, precisaremos alterar em um só lugar
    private static final String PERSISTENCE_UNIT = "FerramentasAlugueis-PU";
    
    private static EntityManager em;
    private static EntityManagerFactory fabrica;
    
    /**
     * Cria e retorna uma instância de EntityManager.
     * Se o EntityManagerFactory não estiver aberto ou se o EntityManager estiver fechado,
     * uma nova instância será criada.
     *
     * @return uma instância de EntityManager
     */
    public static EntityManager getEntityManager() {
        if (fabrica == null || !fabrica.isOpen()) {
            fabrica = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        
        if (em == null || !em.isOpen()) { // cria se em nulo ou se o entity manager foi fechado
            em = fabrica.createEntityManager();
        }

        return em;
    }
    
    /**
     * Fecha o EntityManager e o EntityManagerFactory.
     * Certifica-se de que o EntityManager está aberto antes de fechá-lo.
     */
    public static void closeEntityManager() { // Correção do nome do método
        if (em != null && em.isOpen()) {
            em.close();
        }
        // Fechar a fábrica somente se não houver mais EntityManagers em uso
        if (fabrica != null && fabrica.isOpen()) {
            fabrica.close();
        }
    }            
}
