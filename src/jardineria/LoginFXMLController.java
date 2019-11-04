/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jardineria;

import DAO.DAOUsuario;
import hibernate.Usuarios;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class LoginFXMLController implements Initializable {

    @FXML
    private Button btnLogin;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPWD;
    DAOUsuario daoUsuario = new DAOUsuario();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtUser.setText("root1");
        txtPWD.setText("123");
        // TODO
    }

    @FXML
    private void btnLoginAction(ActionEvent event) {
        Usuarios user = new Usuarios();
        user.setNombre(txtUser.getText());
        user.setContrasenia(txtPWD.getText());
        if (daoUsuario.validaUsuario(user)) {
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("menuFXML.fxml"));
                rootPane.getChildren().setAll(pane);
            } catch (Exception e) {
                System.out.println(""+e);
            }
        }else errorAlert("Error","Usuario", "Usuario o contraseña no validos");
        
        
        
    }
    
    public static void errorAlert(String title, String header, String content){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();	
	}
}
