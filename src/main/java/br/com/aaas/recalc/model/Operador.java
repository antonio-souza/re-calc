package br.com.aaas.recalc.model;

public class Operador {

  private Numero       numero;

  private TipoOperacao tipoOperacao;

  public Operador(Numero numero, TipoOperacao tipoOperacao) {
    super();
    this.numero = numero;
    this.tipoOperacao = tipoOperacao;
  }

  public Operador(Numero numero) {
    super();
    this.numero = numero;
    this.tipoOperacao = null;
  }

  public Numero getNumero() {
    return numero;
  }

  public TipoOperacao getTipoOperacao() {
    return tipoOperacao;
  }

  public Boolean isProduto() {
    return TipoOperacao.MULTIPLICACAO.equals(tipoOperacao) || TipoOperacao.DIVISAO.equals(tipoOperacao);
  }

  @Override
  public String toString() {
    return String.format(" %s %s", numero, tipoOperacao.getSimbolo());
  }
}
