package com.library_ms.libraryms_ui;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.*;

import java.io.IOException;
import java.util.Objects;

public class LoginFormController {
    @FXML
    private Button btnLogin;

    @FXML
    protected void onClickLogin(){
//        lblTest.setText("Thanks for logining in");
        try{
            Stage registerStage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RegisterForm.fxml")));
            Scene scene = new Scene(root);
            registerStage.setScene(scene);
            registerStage.setTitle("Library Application");
            registerStage.show();
//-----------close the form after clicking on the button
            Stage closeStage = (Stage) btnLogin.getScene().getWindow();
            closeStage.close();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


}
