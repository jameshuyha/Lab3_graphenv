package javalab3;

import java.awt.DisplayMode;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import static javafx.scene.input.KeyCode.A;
import static javafx.scene.input.KeyCode.Z;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author 6324151
 */
public class JavaLab3 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        GridPane gridPane = new GridPane();
        root.setCenter(gridPane);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label firstNameLabel = new Label("First Name: ");
        Label lastNameLabel = new Label("Last Name: ");
        Label emailLabel = new Label("Email: ");
        Label passwordLabel = new Label("Password: ");
        Label messageLabel = new Label();

        gridPane.add(firstNameLabel, 0, 0);
        gridPane.add(lastNameLabel, 0, 1);
        gridPane.add(emailLabel, 0, 2);
        gridPane.add(passwordLabel, 0, 3);
        gridPane.add(messageLabel, 0, 5, 2, 1);

        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField emailField = new TextField();
        PasswordField passwordField = new PasswordField();

        gridPane.add(firstNameField, 1, 0);
        gridPane.add(lastNameField, 1, 1);
        gridPane.add(emailField, 1, 2);
        gridPane.add(passwordField, 1, 3);

        Button registerButton = new Button("Register");
        Button clearButton = new Button("Clear");

        gridPane.add(registerButton, 0, 4);
        gridPane.add(clearButton, 1, 4);

        registerButton.setDisable(true);

        clearButton.setOnMouseClicked(e -> {
            firstNameField.clear();
            lastNameField.clear();
            emailField.clear();
            passwordField.clear();
            registerButton.setDisable(true);
        });

        EventHandler textFieldHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (!firstNameField.getText().isEmpty()
                        && !lastNameField.getText().isEmpty()
                        && !emailField.getText().isEmpty()
                        && !passwordField.getText().isEmpty()) {
                    registerButton.setDisable(false);
                } else {
                    registerButton.setDisable(true);
                }
            }
        };
        
        EventHandler registerHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if(emailField.getText().contains("@")
                        && emailField.getText().matches("[a-z0-9A-Z]+@[a-z0-9A-Z]+.[a-z0-9A-Z]")
                        && (passwordField.getText().matches(".*\\d.*[a-zA-Z].*")
                        || passwordField.getText().matches(".*[a-zA-Z].*\\d.*"))) {
                    messageLabel.setText("Welcome");
                } else {
                    messageLabel.setText("Error: Invalid email or password");
                }
            }
        };
        
        firstNameField.setOnKeyReleased(textFieldHandler);
        lastNameField.setOnKeyReleased(textFieldHandler);
        emailField.setOnKeyReleased(textFieldHandler);
        passwordField.setOnKeyReleased(textFieldHandler);
        registerButton.setOnMouseClicked(registerHandler);
        
        Scene scene = new Scene(root, 300, 275);
        primaryStage.setTitle("User Registration Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
