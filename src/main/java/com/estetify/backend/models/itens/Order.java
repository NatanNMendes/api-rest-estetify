package com.estetify.backend.models.itens;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Representa um pedido contendo uma lista de produtos, forma de pagamento e status de pagamento.
 */
public class Order {

    private int id;
    private LocalDate data;
    private final List<Product> produtos;
    private BigDecimal valorTotal;
    private String formaPagamento;
    private boolean pago;

    public Order(int id, LocalDate data, List<Product> produtos, String formaPagamento, boolean pago) {
        this.id = id;
        this.data = data != null ? data : LocalDate.now();
        this.produtos = new ArrayList<>();
        this.formaPagamento = formaPagamento != null ? formaPagamento : "Indefinida";
        this.pago = pago;
        setProdutos(produtos);
    }

    public Order() {
        this.produtos = new ArrayList<>();
        this.valorTotal = BigDecimal.ZERO;
        this.data = LocalDate.now();
        this.formaPagamento = "Indefinida";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("ID não pode ser negativo.");
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = Objects.requireNonNullElse(data, LocalDate.now());
    }

    public List<Product> getProdutos() {
        return Collections.unmodifiableList(produtos);
    }

    public void setProdutos(List<Product> produtos) {
        this.produtos.clear();
        if (produtos != null) {
            for (Product produto : produtos) {
                if (produto != null) {
                    this.produtos.add(produto);
                }
            }
        }
        calcularValorTotal();
    }

    public void adicionarProduto(Product produto) {
        if (produto != null) {
            produtos.add(produto);
            valorTotal = valorTotal.add(BigDecimal.valueOf(produto.getPreco()));
        }
    }

    public void removerProduto(Product produto) {
        if (produto != null && produtos.remove(produto)) {
            valorTotal = valorTotal.subtract(BigDecimal.valueOf(produto.getPreco()));
        }
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento != null ? formaPagamento : "Indefinida";
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    private void calcularValorTotal() {
        valorTotal = produtos.stream()
                .filter(Objects::nonNull)
                .map(p -> BigDecimal.valueOf(p.getPreco()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Retorna um resumo do pedido como String formatada.
     */
    public String getResumoPedido() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido ID: ").append(id).append("\n");
        sb.append("Data: ").append(data).append("\n");

        sb.append("Produtos:\n");
        if (produtos.isEmpty()) {
            sb.append("- Nenhum produto no pedido.\n");
        } else {
            for (Product p : produtos) {
                sb.append(String.format("- %s: R$ %.2f%n", p.getNome(), p.getPreco()));
            }
        }

        sb.append(String.format("Valor Total: R$ %.2f%n", valorTotal));
        sb.append("Forma de Pagamento: ").append(formaPagamento).append("\n");
        sb.append("Pago: ").append(pago ? "Sim" : "Não").append("\n");

        return sb.toString();
    }

    /**
     * Exibe o resumo do pedido no console.
     */
    public void exibirResumo() {
        System.out.println(getResumoPedido());
    }
}
