package br.com.aaas.recalc.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ReCalc extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("ReCalc.fxml"));
    primaryStage.setTitle("Re Calc");
    primaryStage.setScene(new Scene(root, 340, 460));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
