package br.com.aaas.recalc.model;

import java.math.BigDecimal;

@FunctionalInterface
interface Operacao {
  BigDecimal aplicar(BigDecimal a, BigDecimal b);
}
