/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.aluguel.ferramentasaluguel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danie
 */
@Entity
@Table(name = "reservas")
public class Reservas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate data_locacao;
    private LocalDate data_prevista;
    private double preco_total;

    @ManyToOne
    @JoinColumn(name = "fk_clientes")
    private Clientes cliente;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ferramentas_reservas",
            joinColumns = @JoinColumn(name = "fk_reserva"),
            inverseJoinColumns = @JoinColumn(name = "fk_ferramenta")
    )
    private List<Ferramentas> ferramentas = new ArrayList<>();


    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData_locacao() {
        return data_locacao;
    }

    public void setData_locacao(LocalDate data_locacao) {
        this.data_locacao = data_locacao;
    }

    public LocalDate getData_prevista() {
        return data_prevista;
    }

    public void setData_prevista(LocalDate data_prevista) {
        this.data_prevista = data_prevista;
    }

    public double getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(double preco_total) {
        this.preco_total = preco_total;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }



    public List<Ferramentas> getFerramentas() {
        return ferramentas;
    }

    public void setFerramentas(List<Ferramentas> ferramentas) {
        this.ferramentas = ferramentas;
    }
    
    @Override
public String toString() {
    return "Reservas{" +
            "id=" + id +
            ", data_locacao=" + data_locacao +
            ", data_prevista=" + data_prevista +
            ", preco_total=" + preco_total +
            ", cliente=" + (cliente != null ? cliente.getNome() : "Nenhum") + // Evita NullPointerException
            ", ferramentas=" + getFerramentasNomes() +
            '}';
}

private String getFerramentasNomes() {
    if (ferramentas == null || ferramentas.isEmpty()) {
        return "Nenhuma";
    }
    return ferramentas.stream().map(Ferramentas::getNome).toList().toString();
}

}
