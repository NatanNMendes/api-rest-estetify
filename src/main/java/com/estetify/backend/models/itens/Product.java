package com.estetify.backend.models.itens;

import java.util.Objects;

/**
 * Representa um produto com ID, nome e preço.
 * Classe preparada para futura integração com JPA e boas práticas.
 */
public class Product {
    private int id;
    private String nome;
    private double preco;

    /**
     * Construtor completo do produto.
     *
     * @param id    Identificador único do produto.
     * @param nome  Nome do produto (não pode ser nulo ou vazio).
     * @param preco Preço do produto (não pode ser negativo).
     */
    public Product(int id, String nome, double preco) {
        setId(id);
        setNome(nome);
        setPreco(preco);
    }

    /**
     * Construtor vazio (recomendado para frameworks como JPA ou Jackson).
     */
    public Product() {}

    // --- Getters e Setters com validações básicas ---

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
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser nulo ou vazio.");
        }
        this.nome = nome.trim();
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco < 0) throw new IllegalArgumentException("Preço não pode ser negativo.");
        this.preco = preco;
    }

    /**
     * Exibe as informações do produto no console.
     */
    public void exibirInfo() {
        System.out.printf("Produto: %s | Preço: R$%.2f%n", nome, preco);
    }

    // --- Métodos utilitários ---

    @Override
    public String toString() {
        return String.format("Produto{id=%d, nome='%s', preco=R$%.2f}", id, nome, preco);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product produto = (Product) o;
        return id == produto.id && Double.compare(produto.preco, preco) == 0 && Objects.equals(nome, produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, preco);
    }
}
