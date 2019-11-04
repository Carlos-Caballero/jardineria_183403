/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jardineria;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

    
import javafx.scene.chart.LineChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import sqlcontroller.ISqlController;

/**
 *
 * @author vramos
 */
public class FXMLDocumentController_1 implements Initializable {
    
   
    @FXML
    private LineChart<String, Integer> chartBarras;
    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> leyenda;
    private int ultvalor;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
        Button action = (Button)event.getSource();
        String cadena = action.getText();
        Statement st = st = ISqlController.getConnection();
        switch(cadena){
            case "Generar Grafica Productos por Condicion":
                grafica("SELECT nombreEstado,COUNT(descripcionEstado) from productos p join estadoproducto e on p.id = e.productosId GROUP BY e.idEstado, e.nombreEstado","Productos por Condicion",st);
                System.out.println(cadena+1);
                break;
            case "Generar Grafica Fotografias por productos": 
                grafica("SELECT nombre,COUNT(fotografia) from productos p inner join fotografias f on p.id = f.productosId GROUP BY p.nombre","Fotografias por Producto",st);
                System.out.println(cadena+2);
                break;
            case "Generar Grafica Cantidad Producto por tipo de Producto":
                grafica("SELECT nombreTipo,COUNT(*) from productos p join tipoproducto t on p.tipoProducto = t.id GROUP by t.id","Producto por tipo de Producto",st);
                System.out.println(cadena+3);
                break;
            case "Generar Grafica de Riegos por Dia":
                grafica("select r.fecha,count(*) from productos p join riegos r on p.id = r.productosId group by r.fecha","Riegos por dia",st);
                System.out.println(cadena+4);
                break;
        }
    }

    public void grafica(String consulta,String nombreConsulta,Statement st) throws SQLException{
        chartBarras.getData().clear();
        xAxis.getCategories().clear();
        /*String url = "jdbc:mysql://localhost:3306/jardineria?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatatimeCode=false&serverTimezone=UTC";  //cambiar con los datos propios
 	Connection conn = DriverManager.getConnection(url,"root","root"); //cambiar con los datos propios
        Statement st = conn.createStatement();*/
        
        String sql = consulta;
        System.out.println("SQL " + sql);
        ResultSet rs = st.executeQuery(sql);
            
        XYChart.Series<String, Integer> dataSeries1 = new XYChart.Series<>();
        // crea la variable para almacenar las leyendas
        ObservableList<String> leyenda =  FXCollections.observableArrayList();
        while (rs.next()){
            leyenda.add(""+rs.getString(1)); // se agrega como leyenda el campo que que quiera, debe coincidir con la serie
            dataSeries1.getData().add(new XYChart.Data<>(""+rs.getString(1), rs.getInt(2))); // posicion 1 es la leyenda
        }
        dataSeries1.setName(nombreConsulta);
        xAxis.setCategories(leyenda);  // se asigna la leyenda a la grafica
        chartBarras.getData().addAll(dataSeries1); // se agrega la serie de datos
    }

    
}
