package com.estetify.backend.models.itens;

import jakarta.persistence.*; // Se estiver usando JPA com Jakarta
import java.util.Objects;

/**
 * Entidade que representa um produto.
 * Preparada para integração com frameworks como JPA e Jackson.
 */
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private double preco;

    /**
     * Construtor completo com validações.
     *
     * @param id    ID do produto (deve ser ≥ 0).
     * @param nome  Nome do produto (não nulo ou vazio).
     * @param preco Preço do produto (≥ 0).
     */
    public Produto(int id, String nome, double preco) {
        setId(id);
        setNome(nome);
        setPreco(preco);
    }

    /**
     * Construtor padrão necessário para frameworks como JPA.
     */
    public Produto() {
        // Recomendado manter vazio
    }

    // --- Getters e Setters com validações robustas ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("ID não pode ser negativo.");
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        Objects.requireNonNull(nome, "Nome não pode ser nulo.");
        nome = nome.trim();
        if (nome.isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser vazio.");
        }
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco < 0) throw new IllegalArgumentException("Preço não pode ser negativo.");
        this.preco = preco;
    }

    // --- Métodos utilitários ---

    /**
     * Exibe os dados do produto no console.
     */
    public void exibirInfo() {
        System.out.printf("🛒 Produto: %s | 💲 Preço: R$ %.2f%n", nome, preco);
    }

    @Override
    public String toString() {
        return String.format("Produto{id=%d, nome='%s', preco=R$%.2f}", id, nome, preco);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto that = (Produto) o;
        return id == that.id &&
                Double.compare(that.preco, preco) == 0 &&
                Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, preco);
    }

    // --- Padrão opcional de criação com Builder (recomendado para grandes modelos) ---
    /*
    public static class Builder {
        private int id;
        private String nome;
        private double preco;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder preco(double preco) {
            this.preco = preco;
            return this;
        }

        public Produto build() {
            return new Produto(id, nome, preco);
        }
    }
    */
}
