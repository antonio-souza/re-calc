package br.com.aaas.recalc.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

/*
 * Efetua os calculos levando em conta a precedencia.
 * Exemplo:
 * 2 + 3 * 10 / 2 - 5 * 2 = 
 * 2 + 15 - 10 =
 * 7
 * */
public class Calculo {

  private Numero resultado;

  public Calculo(List<Operador> operadores) {
    super();
    List<Operador> adicoesSubtracoes = this.multiplicarEDividir(operadores); 
    resultado = this.somarESubtrair(adicoesSubtracoes);
  }
  
  private List<Operador> multiplicarEDividir(List<Operador> operadores) {
    CalculoComPrecedencia calculoProduto = operadores.stream()
        .collect(
            () -> new CalculoComPrecedencia(),
            (c, o) -> c.adicionarOperador(o),
            (c1, c2) -> c1.combinar(c2)
            );
    calculoProduto.adicionarOperador(new Operador(calculoProduto.getProduto(), TipoOperacao.IGUALDADE));
    return calculoProduto.getAdicoesSubtracoes();    
  }

  private Numero somarESubtrair(List<Operador> adicoesSubtracoes) {    
    return IntStream.range(0, adicoesSubtracoes.size())
      .mapToObj(i -> new Operador(adicoesSubtracoes.get(i).getNumero(), i == 0 ? TipoOperacao.SOMA : adicoesSubtracoes.get(i - 1).getTipoOperacao()))
      .reduce(new Operador(new Numero(BigDecimal.ZERO)), (r, o) -> new Operador(r.getNumero().aplicar(o.getTipoOperacao(), o.getNumero())))
      .getNumero();
  }

  public Numero getResultado() {
    return resultado;
  }
}
