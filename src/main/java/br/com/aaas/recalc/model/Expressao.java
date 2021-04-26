package br.com.aaas.recalc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Expressao {

  private List<Operador> operadores;

  private StringBuilder  numeroBuilder;

  private Boolean        contaFinalizada;

  public Expressao() {
    super();
    this.numeroBuilder = new StringBuilder("0");
    this.reinicializar();
  }

  private void reinicializar() {
    this.operadores = new ArrayList<>();
    contaFinalizada = false;
  }

  public void limparUltimoDigito() {
    if (contaFinalizada) {
      this.reinicializar();
    }
    int tamanho = numeroBuilder.length();
    numeroBuilder = new StringBuilder(numeroBuilder.toString().substring(0, tamanho - 1));
  }

  public void limparNumero() {
    if (contaFinalizada) {
      this.reinicializar();
    }
    numeroBuilder = new StringBuilder("0");
  }

  public void inserirDigito(String digito) {
    if (contaFinalizada) {
      this.numeroBuilder = new StringBuilder("0");
      this.reinicializar();
    }
    //    if (new Numero(numeroBuilder.toString()).toString().length() > 10) {
    //      Float numero = Float.valueOf(numeroBuilder.toString());
    //      return numero.toString();
    //    }
    numeroBuilder.append(digito);
    Numero numero = new Numero(numeroBuilder.toString());
    numeroBuilder = new StringBuilder(numero.toString());
  }

  public void inserirVirgula() {
    if (contaFinalizada) {
      this.numeroBuilder = new StringBuilder("0");
      this.reinicializar();
    }
    if (numeroBuilder.toString().contains(",")) {
      return;
    }
    numeroBuilder.append(",");
  }

  public void trocarSinal() {
    if (contaFinalizada) {
      this.numeroBuilder = new StringBuilder("0");
      this.reinicializar();
    }
    Numero numero = new Numero(numeroBuilder.toString());
    numero = numero.aplicar(TipoOperacao.MULTIPLICACAO, new Numero("-1"));
    numeroBuilder = new StringBuilder(numero.toString());
  }

  public void aplicarOperacao(TipoOperacao tipoOperacao) {
    if (contaFinalizada) {
      this.reinicializar();
    }
    Numero numero = new Numero(numeroBuilder.toString());
    operadores.add(new Operador(numero, tipoOperacao));
    limparNumero();
  }
  
  public void calcular() {
    if (contaFinalizada) {
      this.reinicializar();
    }
    Numero numero = new Numero(numeroBuilder.toString());
    operadores.add(new Operador(numero, TipoOperacao.IGUALDADE));
    
    Numero resultado = new Calculo(operadores).getResultado();

    numeroBuilder = new StringBuilder(resultado.toString());
    contaFinalizada = true;
  }

  public String getNumero() {
    return formatarNumero(numeroBuilder.toString());
  }

  private static String formatarNumero(String string) {
    if (string.length() > 16) {
      Float numero = Float.valueOf(string);
      return numero.toString();
    }
    return string;
  }

  public String getOperadores() {    
    return operadores.stream()
        .map(o -> formatarNumero(o.toString()))
        .collect(Collectors.joining());
  }
}
