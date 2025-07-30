package com.estetify.backend.models.itens;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * Representa um pedido contendo uma lista de produtos, data, forma de pagamento e status de pagamento.
 */
// @Entity
// @Table(name = "pedidos")
public class Pedido {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // @Column(nullable = false)
    private LocalDate data;

    // Relação 1:N com Produto (simulada sem JPA aqui)
    private final List<Produto> produtos = new ArrayList<>();

    private BigDecimal valorTotal = BigDecimal.ZERO;
    private String formaPagamento = "Indefinida";
    private boolean pago = false;

    /**
     * Construtor completo com validações e valores padrão seguros.
     */
    public Pedido(int id, LocalDate data, List<Produto> produtos, String formaPagamento, boolean pago) {
        setId(id);
        setData(data);
        setProdutos(produtos);
        setFormaPagamento(formaPagamento);
        this.pago = pago;
    }

    /**
     * Construtor padrão (recomendado para frameworks como JPA ou Jackson).
     */
    public Pedido() {
        this.data = LocalDate.now();
        this.valorTotal = BigDecimal.ZERO;
        this.formaPagamento = "Indefinida";
    }

    // --- Getters e Setters ---

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

    public List<Produto> getProdutos() {
        return Collections.unmodifiableList(produtos);
    }

    /**
     * Substitui a lista de produtos e atualiza o valor total.
     *
     * @param produtos Lista de produtos válida.
     */
    public void setProdutos(List<Produto> produtos) {
        this.produtos.clear();
        if (produtos != null) {
            produtos.stream()
                    .filter(Objects::nonNull)
                    .forEach(this::adicionarProduto);
        }
        calcularValorTotal();
    }

    public void adicionarProduto(Produto produto) {
        if (produto != null) {
            produtos.add(produto);
            valorTotal = valorTotal.add(BigDecimal.valueOf(produto.getPreco()));
        }
    }

    public void removerProduto(Produto produto) {
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
        this.formaPagamento = formaPagamento != null && !formaPagamento.trim().isEmpty()
                ? formaPagamento.trim()
                : "Indefinida";
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    /**
     * Recalcula o valor total com base nos produtos atuais.
     */
    private void calcularValorTotal() {
        this.valorTotal = produtos.stream()
                .map(p -> BigDecimal.valueOf(p.getPreco()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Retorna um resumo textual formatado do pedido.
     */
    public String getResumoPedido() {
        StringBuilder sb = new StringBuilder();
        sb.append("🧾 Pedido ID: ").append(id).append("\n");
        sb.append("📅 Data: ").append(data).append("\n");

        sb.append("📦 Produtos:\n");
        if (produtos.isEmpty()) {
            sb.append("- Nenhum produto adicionado.\n");
        } else {
            produtos.forEach(p ->
                    sb.append(String.format("- %s: R$ %.2f%n", p.getNome(), p.getPreco()))
            );
        }

        sb.append(String.format("💰 Valor Total: R$ %.2f%n", valorTotal));
        sb.append("💳 Forma de Pagamento: ").append(formaPagamento).append("\n");
        sb.append("✅ Pago: ").append(pago ? "Sim" : "Não").append("\n");

        return sb.toString();
    }

    /**
     * Exibe o resumo do pedido no console.
     */
    public void exibirResumo() {
        System.out.println(getResumoPedido());
    }

    // --- equals, hashCode e toString podem ser adicionados se necessário para comparação ou persistência ---
}
