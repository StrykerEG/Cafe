package com.example.cafe;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterWindowApp extends Application {


    public void show(Stage primaryStage) {
        primaryStage.setTitle("Register Window");

        // Create a GridPane to hold the register components
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));

        // Create labels, text fields, and button
        Label titleLabel = new Label("Register");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        GridPane.setColumnSpan(titleLabel, 2);
        gridPane.add(titleLabel, 0, 0);

        Label usernameLabel = new Label("Username:");
        gridPane.add(usernameLabel, 0, 1);
        TextField usernameField = new TextField();

        gridPane.add(usernameField, 1, 1);

        Label passwordLabel = new Label("Password:");
        gridPane.add(passwordLabel, 0, 2);
        PasswordField passwordField = new PasswordField();
        gridPane.add(passwordField, 1, 2);

        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        GridPane.setColumnSpan(registerButton, 2);
        Button backButton = new Button("Back");
        gridPane.add(backButton,2,2);
        try {
            Image backImage = new Image("A:\\Java apps\\Cafe\\src\\Images\\back.png");
            ImageView backimageview = new ImageView(backImage);
            backimageview.setFitWidth(24); // Adjust the width of the image as needed
            backimageview.setFitHeight(24);
            backButton.setGraphic(backimageview);
        }catch (Exception E)
        {
            System.out.println("we didn't find the file of photo");
        }
        backButton.setOnAction(e->{
            CoffeeShopApp app = new CoffeeShopApp ();
            app.start(primaryStage);
        });
        gridPane.add(registerButton, 0, 3);
        registerButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            final String DB_URL = "jdbc:mysql://localhost:3306/ziad";
            final String DB_USERNAME = "root";
            final String DB_PASSWORD = "password123!";
            // Perform SQL database connection and insertion
            // Thread for writing user data to a file
            Thread fileThread = new Thread(() -> {
                try (FileWriter fileWriter = new FileWriter("user_data.txt", true)) {
                    fileWriter.write(username + "," + password + System.lineSeparator());
                    fileWriter.flush();

                    System.out.println("Registration successful. User data saved to file.");

                } catch (IOException e) {
                    System.out.println("Error occurred while saving user data to file.");
                    e.printStackTrace();
                }
            });

            // Thread for inserting data into the database
            Thread dbThread = new Thread(() -> {
                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
                    String insertQuery = "INSERT INTO usersdb (username, password) VALUES (?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);
                    preparedStatement.executeUpdate();

                    System.out.println("Registration successful. User data inserted into the database.");

                } catch (SQLException e) {
                    System.out.println("Error occurred while inserting user data into the database.");
                    e.printStackTrace();
                }
            });

            // Start both threads
            fileThread.start();
            dbThread.start();

            // Wait for both threads to complete
            try {
                fileThread.join();
                dbThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        BorderPane root = new BorderPane();
        root.setCenter(gridPane);

        Scene scene = new Scene(root, 300, 200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @Override
    public void start(Stage stage) throws Exception {

    }
}
