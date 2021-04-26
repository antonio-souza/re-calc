package br.com.aaas.recalc.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum TipoOperacao implements Operacao {
  MULTIPLICACAO("x", true, BigDecimal::multiply), 
  DIVISAO("/", true, (a, b) -> a.divide(b, 3, RoundingMode.FLOOR)),
  SOMA("+", false, BigDecimal::add), 
  SUBTRACAO("-", false, BigDecimal::subtract),   
  IGUALDADE("=", false, null);

  private String   simbolo;

  private Boolean  comPrecedencia;

  private Operacao operacao;

  TipoOperacao(String simbolo, Boolean comPrecedencia, Operacao operacao) {
    this.simbolo = simbolo;
    this.comPrecedencia = comPrecedencia;
    this.operacao = operacao;
  }

  public String getSimbolo() {
    return simbolo;
  }

  public Boolean isComPrecedencia() {
    return comPrecedencia;
  }

  @Override
  public BigDecimal aplicar(BigDecimal a, BigDecimal b) {
    return operacao == null ? null : operacao.aplicar(a, b);
  }
}
