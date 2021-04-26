package br.com.aaas.recalc.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CalculoComPrecedencia {
  private Numero produto;
  private TipoOperacao tipoOperacao;
  private List<Operador> adicoesSubtracoes;
  
  public CalculoComPrecedencia() {
    super();
    this.produto = new Numero(BigDecimal.ONE);
    this.tipoOperacao = TipoOperacao.MULTIPLICACAO;
    this.adicoesSubtracoes = new ArrayList<>();
  }
  
  public void adicionarOperador(Operador operador) {
    if (tipoOperacao.isComPrecedencia()) {
      produto = produto.aplicar(tipoOperacao, operador.getNumero());

    } else {
      adicoesSubtracoes.add(new Operador(produto, tipoOperacao));
      produto = operador.getNumero();
    }
    tipoOperacao = operador.getTipoOperacao();
  }

  public void combinar(CalculoComPrecedencia outro) {
    adicoesSubtracoes.addAll(outro.adicoesSubtracoes);
  }

  public List<Operador> getAdicoesSubtracoes() {
    return adicoesSubtracoes;
  }
  
  public Numero getProduto() {
    return produto;
  }
}
