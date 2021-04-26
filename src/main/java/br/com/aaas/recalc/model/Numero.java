package br.com.aaas.recalc.model;

import java.math.BigDecimal;

public class Numero {
  private BigDecimal bigDecimal;

  public Numero(BigDecimal bigDecimal) {
    super();
    this.bigDecimal = bigDecimal;
  }

  public Numero(String string) {
    super();
    this.bigDecimal = new BigDecimal(string.replace(",", "."));
  }

  public BigDecimal getBigDecimal() {
    return bigDecimal;
  }

  @Override
  public String toString() {
    String string = bigDecimal.toString();
    return string.replace(".", ",");
  }
  
  public Numero aplicar(TipoOperacao tipoOperacao, Numero outro) {
    BigDecimal resultado = tipoOperacao.aplicar(bigDecimal, outro.bigDecimal);
    return resultado == null ? null : new Numero(resultado);
  }
}
