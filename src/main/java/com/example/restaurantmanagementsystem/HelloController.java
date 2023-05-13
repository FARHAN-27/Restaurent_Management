package com.example.restaurantmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static com.example.restaurantmanagementsystem.Loader.man;
import static com.example.restaurantmanagementsystem.Loader.names;

public class HelloController {

    private Parent root;
    private Stage stage;

    @FXML
    private Label forgot_pw;

    @FXML
    private Button login;

    @FXML
    private AnchorPane mainloginpane;

    @FXML
    private PasswordField password;

    @FXML
    private Label pw_label;

    @FXML
    private StackPane stackpane;

    @FXML
    private TextField username;

    @FXML
    private Label username_label;

    @FXML
    private Label welcome;
    public void go_to_Dashboard(MouseEvent event) throws IOException {
        String email = username.getText();
        String pass = password.getText();

        // search for the user in the file
        boolean found = false;
        try (Scanner scanner = new Scanner(new File("user_details.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                if (parts.length == 3 && email.equals(parts[1]) && pass.equals(parts[2])) {
                    found = true;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (found) {
            // navigate to dashboard
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            FXMLLoader loader= new FXMLLoader(getClass().getResource("dashboard.fxml"));
            root = loader.load();
            Dashboard_to_others_Controller dtoc = loader.getController();
            dtoc.set_money(String.valueOf(man.today_sale));
            dtoc.set_sales1(String.valueOf(man.today_order_count));
            dtoc.set_sales11(String.valueOf(man.on_the_way));
            dtoc.set_sales111(new Date().toString());
            Show.dis_play(root,event);
        } else {
            // show error message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText("Incorrect Credentials");
            alert.setContentText("Please re-enter the credentials and try again.");
            alert.showAndWait();
        }
    }

    public void go_to_Signup(MouseEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        root = FXMLLoader.load(getClass().getResource("Sign_Up.fxml"));
        Show s = new Show();
        s.display(stage,"Sign_Up.fxml");
//        Show.dis_play(root,event);
//        Show s = new Show();
//        s.dis_play(root,e);
    }

    public void display_message(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);// line 1
        alert.setTitle("Sorry!");// line 2
        alert.setHeaderText("A case of forgetfulness :')");// line 3
        alert.setContentText("If you forgot your password, please contact the admin.");// line 4
        alert.showAndWait();
    }
}
