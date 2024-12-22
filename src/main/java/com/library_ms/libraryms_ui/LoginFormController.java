package com.library_ms.libraryms_ui;
import com.library_ms.libraryms_ui.domain.Staff;
import com.library_ms.libraryms_ui.domain.User;
import com.library_ms.libraryms_ui.storage.UserStorage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.*;

import java.util.Objects;

public class LoginFormController {
    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtUn;
    @FXML
    private TextField txtPw;


    @FXML
    protected void onClickLogin(){
//        lblTest.setText("Thanks for logining in");
        if(Objects.equals(txtUn.getText(), "") || Objects.equals(txtPw.getText(), "")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Library_UI");
            alert.setHeaderText("Invalid Login");
            alert.setContentText("Please enter username and password");
            alert.showAndWait();
            return;
        }else{
            // Verify user credentials
            User loginUser = new UserStorage().getRegisteredUser();
            if(loginUser == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Library_UI");
                alert.setHeaderText("No registered user");
                alert.setContentText("Please register first");
                alert.showAndWait();
                return;
            }else if(Objects.equals(loginUser.getUsername(), txtUn.getText()) && Objects.equals(loginUser.getPassword(), txtPw.getText())){
                // If valid, open the main application window
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Successful");
                alert.setHeaderText("Welcome");
                alert.setContentText("You have successfully logged in");
                alert.showAndWait();

                //show dashboard
                try{
                    Stage dashboardStage = new Stage();
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dashboard.fxml")));
                    Scene scene = new Scene(root);
                    dashboardStage.setScene(scene);
                    dashboardStage.setTitle("Library Application");
                    dashboardStage.show();
               //-----------close the form after clicking on the button
                    Stage closeStage = (Stage) btnLogin.getScene().getWindow();
                    closeStage.close();

                }catch(Exception e){
                    e.printStackTrace();
                    e.getCause();
                }

            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Login");
                alert.setContentText("Incorrect username or password");
                alert.showAndWait();
                return;
            }
        }

    }
    @FXML
    protected void onClickReset(){
        txtUn.setText("");
        txtPw.setText("");
    }
    @FXML
    protected void onClickGoToRegister(){
        try{
            Stage registerStage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("registerForm.fxml")));
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
