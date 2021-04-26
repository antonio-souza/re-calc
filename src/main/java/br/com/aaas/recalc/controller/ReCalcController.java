package br.com.aaas.recalc.controller;

import br.com.aaas.recalc.model.Expressao;
import br.com.aaas.recalc.model.TipoOperacao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ReCalcController {

  @FXML
  private Button    btn0;

  @FXML
  private Button    btn2;

  @FXML
  private Button    btn5;

  @FXML
  private Button    btn8;

  @FXML
  private Button    btn9;

  @FXML
  private Button    btn7;

  @FXML
  private Button    btn6;

  @FXML
  private Button    btn3;

  @FXML
  private Button    btn4;

  @FXML
  private Button    btn1;

  @FXML
  private Button    btnIgual;

  @FXML
  private Button    btnSoma;

  @FXML
  private Button    btnSubtracao;

  @FXML
  private Button    btnMultiplicacao;

  @FXML
  private Button    btnDivisao;

  @FXML
  private Button    btnApagarTudo;

  @FXML
  private Button    btnApagarDigito;

  @FXML
  private Button    btnApagarNumero;

  @FXML
  private Button    btnVirgula;

  @FXML
  private Button    btnTrocaSinal;

  @FXML
  private Label     labOperacao;

  @FXML
  private Label     labNumero;

  @FXML
  private Label     labNumeroMenor;

  private Expressao expressao;

  @FXML
  public void initialize() {
    expressao = new Expressao();
    atualizarExibicao();
  }

  @FXML
  void pressionarDigito(ActionEvent event) {
    Button btn = (Button) event.getSource();
    String idBotao = btn.getId();
    int tamanho = idBotao.length();
    String digito = idBotao.substring(tamanho - 1, tamanho);
    expressao.inserirDigito(digito);
    atualizarExibicao();
  }

  @FXML
  void pressionarVirgula(ActionEvent event) {
    expressao.inserirVirgula();
    atualizarExibicao();
  }

  @FXML
  void trocarSinal(ActionEvent event) {
    expressao.trocarSinal();
    atualizarExibicao();
  }

  @FXML
  void aplicarOperacao(ActionEvent event) {
    Button btn = (Button) event.getSource();
    String nomeOperacao = btn.getId().substring(3).toUpperCase();
    expressao.aplicarOperacao(TipoOperacao.valueOf(nomeOperacao));
    atualizarExibicao();
  }

  @FXML
  void apagarDigito(ActionEvent event) {
    expressao.limparUltimoDigito();
    atualizarExibicao();
  }

  @FXML
  void apagarTudo(ActionEvent event) {
    expressao = new Expressao();
    atualizarExibicao();
  }

  @FXML
  void apagarNumero(ActionEvent event) {
    expressao.limparNumero();
    atualizarExibicao();
  }

  @FXML
  void calcular(ActionEvent event) {
    expressao.calcular();
    atualizarExibicao();
  }

  @FXML
  void teclar(KeyEvent event) {
    if (event.getCode().isDigitKey()) {
      String nomeDigito = event.getCode().name();
      expressao.inserirDigito(nomeDigito.substring(nomeDigito.length() - 1));
      atualizarExibicao();

    } else if (KeyCode.COMMA.equals(event.getCode())) {
      expressao.inserirVirgula();
      atualizarExibicao();

    } else if (KeyCode.ADD.equals(event.getCode())) {
      expressao.aplicarOperacao(TipoOperacao.SOMA);
      atualizarExibicao();

    } else if (KeyCode.SUBTRACT.equals(event.getCode())) {
      expressao.aplicarOperacao(TipoOperacao.SUBTRACAO);
      atualizarExibicao();

    } else if (KeyCode.X.equals(event.getCode())) {
      expressao.aplicarOperacao(TipoOperacao.MULTIPLICACAO);
      atualizarExibicao();

    } else if (KeyCode.DIVIDE.equals(event.getCode())) {
      expressao.aplicarOperacao(TipoOperacao.DIVISAO);
      atualizarExibicao();

    } else if (KeyCode.ENTER.equals(event.getCode())) {
      expressao.calcular();
      atualizarExibicao();

    } else if (KeyCode.BACK_SPACE.equals(event.getCode())) {
      expressao.limparUltimoDigito();
      atualizarExibicao();

    } else if (KeyCode.DELETE.equals(event.getCode())) {
      expressao = new Expressao();
      atualizarExibicao();
    }
  }

  private void atualizarExibicao() {    
    labNumeroMenor.setText(expressao.getNumero());
    labNumero.setText(expressao.getNumero());
    
    labNumero.setVisible(expressao.getNumero().length() <= 11);
    labNumeroMenor.setVisible(expressao.getNumero().length() > 11);  
    
    labOperacao.setText(expressao.getOperadores());
  }
}
