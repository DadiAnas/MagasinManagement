package com.shop.usersManagement;

import com.shop.usersManagement.User;
import com.shop.usersManagement.UserDaoImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.shop.SceneController;

import java.io.IOException;


public class UserController  {
    SceneController sceneController= SceneController.getSceneController();
    Alert alert=null;
    UserDaoImpl userDaoImp = new UserDaoImpl();
    @FXML
    // The reference of inputText will be injected by the com.shop.productsManagement.FXML loader
    private TextField usernameField;

    @FXML
    // The reference of inputText will be injected by the com.shop.productsManagement.FXML loader
    private PasswordField passwordField;

    public void alertError(String errorName){
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Failer");
        alert.setHeaderText(null);
        alert.setContentText(errorName);
        alert.showAndWait();
    }

    // Add a public no-args constructor
    public UserController()
    {
    }

    @FXML
    private void initialize()
    {
    }
    @FXML
    public void verifyLogin() throws IOException {
        User user = userDaoImp.exist(usernameField.getText(),passwordField.getText());
        if ( user != null){
            System.out.println("Hello "+user.getRole());
            sceneController.setScene("overviewScene");
        }
        else {
            alertError("Username or Password are wrong");
        }
    }
}
