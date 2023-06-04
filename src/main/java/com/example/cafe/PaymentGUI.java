package com.example.cafe;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PaymentGUI extends PaymentSystem
{

    TextField amountField;
    public PaymentGUI() {
        super();
    }

    public void show(Stage primaryStage) {
    Label amountLabel = new Label("Amount:");
    amountField = new TextField();
    Button payButton = new Button("Pay");
        payButton.setOnAction(e -> processPayment());

    // Create a grid pane and add the UI elements
    GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(amountLabel, 0, 0);
        gridPane.add(amountField, 1, 0);
        gridPane.add(payButton, 1, 1);


    Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setScene(scene);


        primaryStage.show();
}

    private void processPayment() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            PaymentSystem paymentSystem = new PaymentSystem(amount,getCharge());
            System.out.println();
                if (Valdis()) {
                    showAlert(Alert.AlertType.INFORMATION, "Payment Successful", "Payment of $" + amount + " was successful!" + "your charge is :" + paymentSystem.calculateCharge());
                } else {
                    System.out.println("not enough money");
                }


            amountField.clear();
        } catch (NumberFormatException e) {

            showAlert(Alert.AlertType.ERROR, "Invalid Amount", "Please enter a valid amount.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
