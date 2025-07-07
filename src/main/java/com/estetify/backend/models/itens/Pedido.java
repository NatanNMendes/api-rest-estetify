package com.estetify.backend.models.itens;

import java.time.LocalDate;
import java.util.List;

public class Pedido {
    private int id;
    private LocalDate data;
    private List<Produto> produtos;
    private double valorTotal;
    private String formaPagamento;
    private boolean pago;

    public Pedido(int id, LocalDate data, List<Produto> produtos, String formaPagamento, boolean pago) {
        this.id = id;
        this.data = data;
        this.produtos = produtos;
        this.formaPagamento = formaPagamento;
        this.pago = pago;
        calcularValorTotal();
    }

    public Pedido() {
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
        calcularValorTotal();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    // Método para calcular o valor total
    private void calcularValorTotal() {
        this.valorTotal = 0;
        if (produtos != null) {
            for (Produto produto : produtos) {
                this.valorTotal += produto.getPreco();
            }
        }
    }

    // Método para exibir os dados do pedido
    public void exibirResumo() {
        System.out.println("Pedido ID: " + id);
        System.out.println("Data: " + data);
        System.out.println("Produtos:");
        for (Produto p : produtos) System.out.println("- " + p.getNome() + " R$" + p.getPreco());
        System.out.println("Valor Total: R$" + valorTotal);
        System.out.println("Forma de Pagamento: " + formaPagamento);
        System.out.println("Pago: " + (pago ? "Sim" : "Não"));
    }
}
