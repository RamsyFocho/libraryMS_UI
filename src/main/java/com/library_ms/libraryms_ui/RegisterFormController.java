package com.library_ms.libraryms_ui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class RegisterFormController {
    @FXML
    private TextField txtFn;
    @FXML
    private TextField txtLn;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPw;
    @FXML
    private Label lblMessage;
    @FXML
    private Label lblLogin;
    
    @FXML
    protected void onRegisterClick(){
        if(Objects.equals(txtFn.getText(), "") || Objects.equals(txtLn.getText(),"") || Objects.equals(txtEmail.getText(),"") || Objects.equals(txtPw.getText(),""))
        {
            lblMessage.setText("Please fill in all the values");
            lblMessage.setStyle("-fx-background-color: red;");
        }else{
            lblMessage.setText("Thanks for registering with us");
            lblMessage.setStyle("-fx-background-color: green;");
        }
    }
    @FXML
    protected void onResetClick(){
        txtFn.setText("");
        txtLn.setText("");
        txtEmail.setText("");
        txtPw.setText("");
    }
    //    event to click the label to go back to the login page
    @FXML
    protected void handleRedirectLogin(){
        try{
            Stage loginStage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginForm.fxml")));
            Scene scene = new Scene(root);
            loginStage.setScene(scene);
            loginStage.setTitle("Library Application");
            loginStage.show();
//-----------close the form after clicking on the button
            Stage closeStage = (Stage) lblLogin.getScene().getWindow();
            closeStage.close();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
