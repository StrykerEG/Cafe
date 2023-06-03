//ziad ahmed mohamed hassan 211010307 CoffeeShopApp
package com.example.cafe;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CoffeeShopApp extends Application {
    private CoffeeShopSystem coffeeShopSystem;

    Cart cart = new Cart();

    public CoffeeShopApp() {}

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        coffeeShopSystem = new CoffeeShopSystem();
        primaryStage.setTitle("قهوه المصريين ");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        // Set the background image
        try {
            Image bg = new Image("C:\\Users\\ziad ahmed\\Documents\\GitHub\\Cafe\\src\\Images\\bg.jpg");
            BackgroundImage bgi = new BackgroundImage(bg, BackgroundRepeat.REPEAT,
                    BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            grid.setBackground(new Background(bgi));
        }catch (Exception e)
        {
            System.out.println("we didn't find the file of photo");
        }
        Label usernameLabel = new Label("Username:");
        usernameLabel.setTextFill(Color.WHITE);
        GridPane.setConstraints(usernameLabel, 0, 0);
        TextField usernameField = new TextField();
        GridPane.setConstraints(usernameField, 1, 0);

        Label passwordLabel = new Label("Password:");
        passwordLabel.setTextFill(Color.WHITE);
        GridPane.setConstraints(passwordLabel, 0, 1);
        PasswordField passwordField = new PasswordField();
        GridPane.setConstraints(passwordField, 1, 1);

        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 2);
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            boolean loginSuccessful = coffeeShopSystem.loginUserFromFile(username, password);
            if (loginSuccessful) {

                showMenuScreen(primaryStage);

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("wrong information");
                alert.setContentText("Invalid username or password.");
                alert.showAndWait();
            }
        });


        Button registerButton = new Button("Register");
        GridPane.setConstraints(registerButton, 1, 3);
        registerButton.setOnAction(e -> {
            RegisterWindowApp r = new RegisterWindowApp();
            r.show(primaryStage);

            String username = usernameField.getText();
            String password = passwordField.getText();
            coffeeShopSystem.registerUser(username,password);

                }
        );
        Button information = new Button("Information");
        information.setStyle("-fx-font-size: 20px; -fx-min-width: 200px; -fx-min-height: 50px;");
        information.setOnAction(e-> ShowInfoScreen(primaryStage));
        grid.add(information,4,7);
        grid.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton,registerButton);

        Scene scene = new Scene(grid, 600, 590);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showMenuScreen(Stage primaryStage) {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Add menu screen components and functionality
        Label titleLabel = new Label("Menu");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        titleLabel.setTextFill(Color.WHITE);
        grid.add(titleLabel, 0, 0, 2, 1);

        // Add buttons for menu items
        Button coffeeButton = new Button("Coffee");
        try {
            Image coffeeimage = new Image("C:\\Users\\ziad ahmed\\Documents\\GitHub\\Cafe\\src\\Images\\coffee.png");
            ImageView coffeeimageView = new ImageView(coffeeimage);
            coffeeimageView.setFitWidth(100); // Adjust the width of the image as needed
            coffeeimageView.setFitHeight(100);
            coffeeButton.setGraphic(coffeeimageView);
        }catch (Exception e)
        {
            System.out.println("we didn't find the file of photo");
        }
        coffeeButton.setOnAction(e->{
            CoffeeItem coffee = new CoffeeItem("Coffee",10,1); // Create a CoffeeItem object and add it to the cart
            cart.addItem(coffee);
            System.out.println("we added Coffee"+" the price  is "+coffee.getTotalPrice());
        });

        Button teaButton = new Button("Tea");
        teaButton.setOnAction(e->{
            CoffeeItem tea = new CoffeeItem("tea",20,1);
            cart.addItem(tea);
            System.out.println("we added tea"+" the price  is "+tea.getTotalPrice());
        });
        try {
            Image teaimage = new Image("C:\\Users\\ziad ahmed\\Documents\\GitHub\\Cafe\\src\\Images\\tea.png");
            ImageView teaImageView = new ImageView(teaimage);
            teaImageView.setFitWidth(100); // Adjust the width of the image as needed
            teaImageView.setFitHeight(100);
            teaButton.setGraphic(teaImageView);
        }catch (Exception e)

        {
            System.out.println("we didn't find the file of photo");
        }


        Button waterb = new Button("water");
        waterb.setOnAction(e->{
            CoffeeItem water = new CoffeeItem("water",30,1);
            cart.addItem(water);
            System.out.println("we added water"+" the price  is "+water.getTotalPrice());

        });
        try {
            Image waterimage = new Image("C:\\Users\\ziad ahmed\\Documents\\GitHub\\Cafe\\src\\Images\\water.png");
            ImageView waterimageview = new ImageView(waterimage);
            waterimageview.setFitHeight(100);
            waterimageview.setFitWidth(100);
            waterb.setGraphic(waterimageview);
        }catch (Exception e )
        {
            System.out.println("we didn't find the file of photo");
        }
        Button cartB = new Button("cart");
        try {
            Image cartImage = new Image("C:\\Users\\ziad ahmed\\Documents\\GitHub\\Cafe\\src\\Images\\cart.jpg");
            ImageView cartImageView = new ImageView(cartImage);
            cartImageView.setFitWidth(100); // Adjust the width of the image as needed
            cartImageView.setFitHeight(100); // Adjust the height of the image as needed
            cartB.setGraphic(cartImageView);
        }catch (Exception e)
        {
            System.out.println("we didn't find the file of photo");
        }
        cartB.setOnAction(e-> showCart());
        Button back = new Button("back");
        try {
            Image backiamge = new Image("C:\\Users\\ziad ahmed\\Documents\\GitHub\\Cafe\\src\\Images\\back.png");
            ImageView backimageview = new ImageView(backiamge);
            backimageview.setFitWidth(100); // Adjust the width of the image as needed
            backimageview.setFitHeight(100);
            back.setGraphic(backimageview);
        }catch (Exception e )
        {
            System.out.println("we didn't find the file of photo");
        }
        back.setOnAction(e-> start(primaryStage));
        try {
            Image bg = new Image("C:\\Users\\ziad ahmed\\Documents\\GitHub\\Cafe\\src\\Images\\star.jpg");
            BackgroundImage bgi = new BackgroundImage(bg, BackgroundRepeat.REPEAT,
                    BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

            grid.setBackground(new Background(bgi));
        }catch (Exception e)
        {
            System.out.println("we didn't find the file of photo");
        }
        grid.add(back,0,3);
        grid.add(coffeeButton, 0, 1);
        grid.add(teaButton, 1, 1);
        grid.add(waterb, 0, 2);
        grid.add(cartB,4,1);



        Scene scene = new Scene(grid, 1300, 1000);
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);


    }


       private void  showCart() {
        Stage cartStage = new Stage();
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setVgap(10);
        pane.setHgap(10);
        Label totalPrice = new Label("total price :" +cart.calculateTotal());
        totalPrice.setTextFill(Color.WHITE);
        totalPrice.setStyle("-fx-font-size: 40px");
        Label totalItems = new Label("      total Items"+cart.HowManyItems());
        totalItems.setStyle("-fx-font-size: 40;");
        totalItems.setTextFill(Color.WHITE);
        Button items = new Button("items");
           items.setStyle("-fx-font-size: 20px; -fx-min-width: 200px; -fx-min-height: 50px;");
        try {
            items.setOnAction(e -> cart.printItems());
        }catch (Exception e)
        {
            System.out.println("the list is null please add some items ");
            e.printStackTrace();
        }

        Button Pay = new Button("Pay");
           Pay.setStyle("-fx-font-size: 20px; -fx-min-width: 200px; -fx-min-height: 50px;");
        Pay.setOnAction(e->{
            PaymentGUI paymentGUI = new PaymentGUI();
            paymentGUI.show(cartStage);
        });

           Button backButton = new Button("Back");
           backButton.setStyle("-fx-font-size: 20px; -fx-min-width: 200px; -fx-min-height: 50px;");
           backButton.setOnAction(e -> {
               // Call the method to show the menu screen
               showMenuScreen(cartStage);
           });
           try {
               Image bg = new Image("C:\\Users\\ziad ahmed\\Documents\\GitHub\\Cafe\\src\\Images\\star.jpg");
               BackgroundImage bgi = new BackgroundImage(bg, BackgroundRepeat.REPEAT,
                       BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

               pane.setBackground(new Background(bgi));
           }catch (Exception e)
           {
               System.out.println("error");
           }
        pane.add(totalPrice,0,1);
        pane.add(totalItems,1,1);
        pane.add(items,0,2);
        pane.add(backButton,0,3);
        pane.add(Pay,4,4);

        Scene scene = new Scene(pane, 800, 800);
        cartStage.setResizable(true);
        cartStage.setScene(scene);
        cartStage.show();

       }
    private void ShowInfoScreen(Stage primaryStage) {
        primaryStage.setTitle("App Info");

        // Create a label to display the information
        Label infoLabel = new Label("Hello, it's me Ziad the developer of قهوه المصريين  i have worked on this project for weeks it's still under development but we have go so far " +
                "the main idea of the app that it allows you to create an account with specific amount of money and you are able to request 3 types of liquids coffee tea and water " +
                "after that you can pay to get your order " +
                "I consider the gui and try catch statements and classes and inheritance and association , aggregation and composition finally the composition"
                + "Enjoy ");

        infoLabel.setWrapText(true); // Enable text wrapping
        infoLabel.setMaxWidth(400); // Set a maximum width for the label
        infoLabel.setPadding(new Insets(10)); // Add some padding for better appearance
        infoLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14)); // Set font and size

        StackPane layout = new StackPane();
        layout.getChildren().add(infoLabel);


        Scene scene = new Scene(layout, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.show();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), infoLabel);
        translateTransition.setFromY(-scene.getHeight()); // Start above the window
        translateTransition.setToY(0); // Move to the original position
        translateTransition.play();
        // Adjust the window size based on the label's preferred size
        double HeightIwant = infoLabel.prefHeight(scene.getWidth()); // Calculate the preferred height
        primaryStage.setHeight(HeightIwant + 50);

    }

}

