package com.example.restaurantmanagementsystem;

import com.example.restaurantmanagementsystem.HelloController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static com.example.restaurantmanagementsystem.Loader.names;

public class Sign_Up_to_Login {

    private Stage stage;

    @FXML
    private TextField email;

    @FXML
    private Label email_label;

    @FXML
    private AnchorPane mainloginpane;

    @FXML
    private PasswordField password;

    @FXML
    private Label pw_label;

    @FXML
    private Button signup;

    @FXML
    private StackPane stackpane;

    @FXML
    private Label toe_lable;

    @FXML
    private TextField username;

    @FXML
    private Label username_label;

    @FXML
    private Label welcome;

    public void back_to_login(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // get user details
        String mail = email.getText();
        String pass = password.getText();
        String name = username.getText();

        // write details to file
        BufferedWriter writer = new BufferedWriter(new FileWriter("user_details.txt", true));
        writer.write(mail + " " + name + " " + pass);
        writer.newLine();
        writer.close();

        // add details to list
        Pair<String, String> pair = new Pair<String, String>(name, pass);
        names.add(pair);

        // navigate to login page
        Unloader u = new Unloader(1);
        Show s = new Show();
        s.display(stage, "Login_Page.fxml");
    }
}
