package com.estetify.backend.models.itens;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private int id;
    private LocalDate data;
    private List<Product> produtos;
    private double valorTotal;
    private String formaPagamento;
    private boolean pago;

    public Order(int id, LocalDate data, List<Product> produtos, String formaPagamento, boolean pago) {
        this.id = id;
        this.data = data;
        this.produtos = produtos;
        this.formaPagamento = formaPagamento;
        this.pago = pago;
        calcularValorTotal();
    }

    public Order() {
        // Construtor vazio
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

    public List<Product> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Product> produtos) {
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
        valorTotal = 0.0;
        if (produtos != null) {
            for (Product produto : produtos) {
                if (produto != null) {
                    valorTotal += produto.getPreco();
                }
            }
        }
    }

    // Método para exibir os dados do pedido
    public void exibirResumo() {
        System.out.println("Pedido ID: " + id);
        System.out.println("Data: " + data);

        System.out.println("Produtos:");
        if (produtos != null && !produtos.isEmpty()) {
            for (Product p : produtos) {
                if (p != null) {
                    System.out.printf("- %s R$%.2f%n", p.getNome(), p.getPreco());
                }
            }
        } else {
            System.out.println("- Nenhum produto no pedido.");
        }

        System.out.printf("Valor Total: R$%.2f%n", valorTotal);
        System.out.println("Forma de Pagamento: " + formaPagamento);
        System.out.println("Pago: " + (pago ? "Sim" : "Não"));
    }
}
