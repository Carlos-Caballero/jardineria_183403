/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jardineria;

import DAO.DAOProducto;
import DAO.DAORiego;
import DAO.DAOTipoproducto;
import DAO.DAOUsuario;
import hibernate.Productos;
import hibernate.Riegos;
import hibernate.Tipoproducto;
import hibernate.Usuarios;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sqlcontroller.ISqlController;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import javafx.scene.control.TextArea;

public class MenuFXMLController implements Initializable {

    @FXML
    private TextField txtIdTipo;

    @FXML
    private TextField txtNombreTipo;

    @FXML
    private TextField txtDescripcionTipo;

  

    @FXML
    private TextArea txtTipos;


    @FXML
    private TextField txtIdRiego;

    @FXML
    private TextField txtNombre, txtID, txtPrecio;
    @FXML
    private AnchorPane reporteAn;

    private Statement st;
    @FXML
    private ComboBox cbTipoProducto = new ComboBox();
    @FXML
    private ComboBox cbCondicionActual = new ComboBox();
    @FXML
    private DatePicker fechaIngreso;
    private int idTipoProducto, idCondicionActual;
    @FXML
    private TableView<Product> tbl_productos;
    @FXML
    private TableColumn<Product, Integer> tblC_Id;
    @FXML
    private TableColumn<Product, String> tblC_Nombre;
    @FXML
    private TableColumn<Product, Integer> tblC_Precio;
    @FXML
    private TableColumn<Product, Integer> tblC_TipoProducto;
    @FXML
    private TableColumn<Product, Integer> tblC_CondicionActual;
    @FXML
    private TableColumn<Product, String> tblC_FechaIngreso;
    @FXML
    private TextField ruta;
    @FXML
    private ImageView img;
    @FXML
    private TextField txtIdFoto;
    @FXML
    private Button btnSig;
    @FXML
    private DatePicker fechaFoto;

    @FXML
    private TextField txtIdPBuscar;
    @FXML
    private TextField txtIdFBuscar;
    @FXML
    private DatePicker fechaBuscar;
    @FXML
    private ImageView img11;
    @FXML
    private TextField txtIdProducto;
    private int posicionEnTabla;
    private ResultSet rs;
    @FXML
    private TextField txtIdRiegoModificar;
    private ObservableList<Product> data = FXCollections.observableArrayList();
    private ObservableList<Riegos> data1 = FXCollections.observableArrayList();

    @FXML
    private ImageView imgSalir;
    DAOProducto daoProducto = new DAOProducto();
    DAOUsuario daoUsuario = new DAOUsuario();
    DAOTipoproducto daotipoProducto = new DAOTipoproducto();
    DAORiego daoriegos = new DAORiego();

    @FXML
    private TextField txtIdProductoRiego;

    @FXML
    private TableView<Riegos> tablaRiegos;

    @FXML
    private TableColumn<?, ?> idRiego;

    @FXML
    private TableColumn<?, ?> diaSemana;

    @FXML
    private TableColumn<?, ?> idProducto;

    @FXML
    private Button btnAgregarRiego;

    @FXML
    private Button btnModificarRiego;

    @FXML
    private Button btnEliminarRiego;

    @FXML
    private DatePicker boxFechaRiego;

    @FXML
    private Button btnBuscarRiego;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image imagen = new Image("file:" + "C:/Users/Carlos/Downloads/Jardineria/src/jardineria/12.jpg");
        imgSalir.setImage(imagen);
        st = ISqlController.startConection("root", "");
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument_1.fxml"));
            reporteAn.getChildren().setAll(pane);
        } catch (Exception e) {

        }
        btnSig.setDisable(true);

        visualizarTabla();

        ObservableList<String> options
                = FXCollections.observableArrayList(
                        "Arbusto",
                        "Arbol",
                        "Planta",
                        "Flor"
                );
        cbTipoProducto.setItems(options);
        boxTipoProductos();

        ObservableList<String> options2
                = FXCollections.observableArrayList(
                        "Buen Estado 1",
                        "Falta De Agua 2",
                        "Exceso De Agua 3",
                        "Plaga 4"
                );
        cbCondicionActual.setItems(options2);

        daoProducto.listProductos();
        visualizarTablaRiegos();
        visualizarTipos();
    }

    @FXML
    public void seleccionComboBox() {
        boxTipoProductos();
        switch (cbTipoProducto.getSelectionModel().getSelectedItem().toString().trim()) {
            case "Arbusto":
                idTipoProducto = 1;
                break;
            case "Arbol":
                idTipoProducto = 2;
                break;
            case "Planta":
                idTipoProducto = 3;
                break;
            case "Flor":
                idTipoProducto = 4;
                break;
        }
    }

    @FXML
    public void seleccionComboBox2() {

        switch (cbCondicionActual.getSelectionModel().getSelectedItem().toString().trim()) {
            case "Buen Estado 1":
                idCondicionActual = 1;
                break;
            case "Falta De Agua 2":
                idCondicionActual = 2;
                break;
            case "Exceso De Agua 3":
                idCondicionActual = 3;
                break;
            case "Con Plaga 4":
                idCondicionActual = 4;
                break;
        }
    }

    @FXML
    private void btnAgregarAction(ActionEvent event) {

        Date date = new Date();

        try {
            Productos producto;
            Tipoproducto t = (Tipoproducto) cbTipoProducto.getSelectionModel().getSelectedItem();
            producto = new Productos(Integer.parseInt(txtID.getText()),
                    txtNombre.getText(),
                    Integer.parseInt(txtPrecio.getText()),
                    t.getId(), idCondicionActual,
                    java.sql.Date.valueOf(fechaIngreso.getValue()));
            daoProducto.crearProducto(producto);

            //
            String query;
            query = "INSERT INTO estadoproducto VALUES (";
            query += idCondicionActual + ",'";
            query += cbCondicionActual.getSelectionModel().getSelectedItem().toString() + "','";
            query += cbCondicionActual.getSelectionModel().getSelectedItem().toString() + "',";
            query += txtID.getText() + ")";
            System.out.println(query);

            ISqlController.executeDataManipulationQuery(query, st);

        } catch (Exception e) {

        }
        visualizarTabla();
    }

    public void visualizarTabla() {
        tbl_productos.getItems().clear();
        data.clear();
        final ObservableList<Product> tblSeleccion = tbl_productos.getSelectionModel().getSelectedItems();
        tblSeleccion.addListener(selectorTablaProducto);
        tblC_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblC_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tblC_Precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tblC_TipoProducto.setCellValueFactory(new PropertyValueFactory<>("tipoProducto"));
        tblC_CondicionActual.setCellValueFactory(new PropertyValueFactory<>("estadoProducto"));
        tblC_FechaIngreso.setCellValueFactory(new PropertyValueFactory<>("fechaIngreso"));
        ArrayList<Productos> productos = daoProducto.listProductos();
        String valores = "";
        for (Productos p : productos) {
            
           // valores += p;
            Product product = new Product(p.getId(), p.getNombre(), p.getPrecio(), daotipoProducto.buscarTipo(p.getTipoProducto()).getNombreTipo(), String.valueOf(p.getEstadoProducto()), java.sql.Date.valueOf(p.getFechaIngreso().toString()));
            data.add(product);
        }
       

        tbl_productos.setItems(data);
    }

    private final ListChangeListener<Product> selectorTablaProducto
            = new ListChangeListener<Product>() {
        @Override
        public void onChanged(ListChangeListener.Change<? extends Product> c) {
            ponerProductoSeleccionado();
        }
    };

    private final ListChangeListener<Riegos> selectorTablaRiego
            = new ListChangeListener<Riegos>() {
        @Override
        public void onChanged(ListChangeListener.Change<? extends Riegos> c) {
            ponerProductoSeleccionadoR();
        }
    };

    private void ponerProductoSeleccionado() {
        final Product producto = getTblProductoSeleccionado();
        posicionEnTabla = data.indexOf(producto);

        if (producto != null) {
            txtID.setText(String.valueOf(producto.getId()));
            txtNombre.setText(producto.getNombre());
            txtPrecio.setText(String.valueOf(producto.getPrecio()));
        }
    }

    private void ponerProductoSeleccionadoR() {
        final Riegos riego = getTblRiegoSeleccionado();
        posicionEnTabla = data.indexOf(riego);

        if (riego != null) {
            txtIdRiegoModificar.setText(String.valueOf(riego.getId()));
            txtIdRiego.setText(String.valueOf(riego.getId()));
        }
    }

    public Product getTblProductoSeleccionado() {
        if (tbl_productos != null) {
            List<Product> tabla = tbl_productos.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Product competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }

    public Riegos getTblRiegoSeleccionado() {
        if (tablaRiegos != null) {
            List<Riegos> tabla = tablaRiegos.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Riegos competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }

    @FXML
    public void datos(ActionEvent event) throws IOException {
        String path = "C:\\Users\\Carlos\\Downloads\\jardineria.mp4";
        //Instantiating Media class  
        Media media = new Media(new File(path).toURI().toString());

        //Instantiating MediaPlayer class   
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        //Instantiating MediaView class   
        MediaView mediaView = new MediaView(mediaPlayer);

        //by setting this property to true, the Video will be played   
        mediaPlayer.setAutoPlay(true);

        //setting group and scene 
        Group root = new Group();
        root.getChildren().add(mediaView);

        Scene scene = new Scene(root, 800, 450);
        scene.getEffectiveNodeOrientation();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
    }

    @FXML
    void eliminar(ActionEvent event) {
        daoProducto.deleteProducto(Integer.parseInt(txtID.getText()));

        visualizarTabla();

    }

    @FXML
    void agregarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");
        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(null);
        // Mostar la imagen
        String a = "\\";
        String ruta2 = "";
        String s = imgFile.getAbsolutePath();
        for (int i = 0; i < s.length(); i++) {
            ruta2 += s.charAt(i);
            if (s.charAt(i) == a.charAt(0)) {
                ruta2 += a.charAt(0);
            }
        }
        System.out.println("ruta2" + ruta2);
        if (imgFile != null) {
            String r = imgFile.getAbsolutePath();

            ruta.setText(ruta2);
            System.out.println("Absolute path " + imgFile.getAbsolutePath());
            Image image = new Image("file:" + imgFile.getAbsolutePath());
            img.setImage(image);
        }
    }

    @FXML
    void agregarFoto(ActionEvent event) {
        String query = "INSERT INTO fotografias VALUES ('"
                + txtIdFoto.getText() + "','"
                + fechaFoto.getValue() + "','"
                + ruta.getText() + "','"
                + txtIdProducto.getText() + "')";

        System.out.println(query);
        ISqlController.executeDataManipulationQuery(query, st);
        img.setImage(null);
    }

    @FXML
    void buscarImagen(ActionEvent event) {
        rs = ISqlController.executeViewQuery("select idFotografia,fotografia from fotografias where productosId = "
                + txtIdPBuscar.getText() + " and "
                + "fecha = '" + fechaBuscar.getValue() + "'", st);
        System.out.println(rs);

        try {
            if (rs.next()) {
                int idFoto = rs.getInt("idFotografia");
                txtIdFBuscar.setText(String.valueOf(idFoto));
                String valor = rs.getString("fotografia");
                System.out.println(valor);
                Image image = new Image("file:" + rs.getString("fotografia"));
                img11.setImage(image);
                btnSig.setDisable(false);
            }

            /*while (rs.next()) {
            Product product = new Product(rs.getInt("id"), rs.getString("nombre"), rs.getInt("precio"), rs.getString("nombretipo"), rs.getString("nombreEstado"), rs.getDate("fechaIngreso"));
            data.add(product);
            }*/
        } catch (SQLException ex) {
            errorAlert("Error", "Datos", "No existen fotografias de ese producto\n en ese dia");
        }
    }

    @FXML
    void sigImagen(ActionEvent event) throws SQLException {
        if (rs.next()) {
            int idFoto = rs.getInt("idFotografia");
            txtIdFBuscar.setText(String.valueOf(idFoto));
            String valor = rs.getString("fotografia");
            System.out.println(valor);
            Image image = new Image("file:" + rs.getString("fotografia"));
            img11.setImage(image);
        } else {
            errorAlert("Error", "Imagenes", "No existen mas imagenes de\nese producto en esa fecha");
            btnSig.setDisable(true);
        }

        /*while (rs.next()) {
            Product product = new Product(rs.getInt("id"), rs.getString("nombre"), rs.getInt("precio"), rs.getString("nombretipo"), rs.getString("nombreEstado"), rs.getDate("fechaIngreso"));
            data.add(product);
        }*/
    }

    public static void errorAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void infoAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void eliminarFoto(ActionEvent event) {
        String sql = "DELETE FROM fotografias WHERE idFotografia=" + txtIdFBuscar.getText();
        System.out.println(sql);
        ISqlController.executeDataManipulationQuery(sql, st);
        img11.setImage(null);
    }

    @FXML
    void editarF(ActionEvent event) {
        String query = "UPDATE fotografias SET idFotografia=" + txtIdFoto.getText()
                + ",fecha='" + fechaFoto.getValue()
                + "',fotografia='" + ruta.getText()
                + "',productosId=" + txtIdProducto.getText()
                + " WHERE  idFotografia = " + txtIdFoto.getText();
        System.out.println(query);
        ISqlController.executeDataManipulationQuery(query, st);
    }

    @FXML
    public void modifyProduct() {

        Tipoproducto t = (Tipoproducto) cbTipoProducto.getSelectionModel().getSelectedItem();

        Product p = data.get(posicionEnTabla);
        System.out.println(p);
        daoProducto.updateProducto(p.getId(), txtNombre.getText(), Integer.parseInt(txtPrecio.getText()), t.getId(), idCondicionActual,
                java.sql.Date.valueOf(fechaIngreso.getValue()));
        data.set(posicionEnTabla, p);
        visualizarTabla();
    }

    @FXML
    void salir(MouseEvent event) throws IOException {
        ISqlController.closeConection(st);
        Parent parent = FXMLLoader.load(getClass().getResource("loginFXML.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();

        Stage stage2 = (Stage) imgSalir.getScene().getWindow();
        // do what you have to do
        stage2.close();
    }

    public void visualizarTablaRiegos() {
        tablaRiegos.getItems().clear();
        data1.clear();
        final ObservableList<Riegos> tblSeleccion = tablaRiegos.getSelectionModel().getSelectedItems();
        tblSeleccion.addListener(selectorTablaRiego);
        idRiego.setCellValueFactory(new PropertyValueFactory<>("id"));
        diaSemana.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        idProducto.setCellValueFactory(new PropertyValueFactory<>("ProductosId"));
        try {

            rs = ISqlController.executeViewQuery("SELECT * FROM productos p join tipoproducto t on p.id = t.productosId\n"
                    + "join estadoproducto e  on p.id = e.productosId GROUP BY p.id,p.nombre,p.precio,p.tipoProducto,p.estadoProducto,p.fechaIngreso,t.idTipoProducto,t.nombreTipo,t.descripcion,t.productosId,e.idEstado,e.nombreEstado,e.descripcionEstado,e.productosId\n"
                    + ";", st);

            ArrayList<Riegos> riegos = daoriegos.listRiegos();

            for (Riegos riego : riegos) {
                data1.add(riego);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        tablaRiegos.setItems(data1);
    }

    

    public void agregarRiego() {
        Riegos riego = new Riegos();
        riego.setFecha(java.sql.Date.valueOf(boxFechaRiego.getValue()));
        riego.setProductosId(Integer.parseInt(txtIdProductoRiego.getText()));
        int id = daoriegos.crearRiego(riego);
        System.out.println("idRiego: "+id);
        System.out.println("el riego es: "+daoriegos.buscarRiego(id));
        System.out.println("el producto es: "+daoProducto.buscarProducto(Integer.parseInt(txtIdProductoRiego.getText())));
        daoProducto.agregarRiego(daoProducto.buscarProducto(Integer.parseInt(txtIdProductoRiego.getText())).getId(), id);
//daoProducto.buscarProducto(Integer.parseInt(txtIdProductoRiego.getText())).setRiego(riego);
        visualizarTablaRiegos();
    }

    public void eliminarRiego() {
        int RiegoID = Integer.parseInt(txtIdRiego.getText());
        daoriegos.deleteRiego(RiegoID);
        visualizarTablaRiegos();
    }

    public void ModificarRiego() {
        int RiegoID = Integer.parseInt(txtIdRiegoModificar.getText());
        daoriegos.updateRiego(RiegoID, java.sql.Date.valueOf(boxFechaRiego.getValue()), Integer.parseInt(txtIdProductoRiego.getText()));
        visualizarTablaRiegos();
    }

    public void visualizarTipos() {
        txtTipos.clear();
        String valores = "";
        ArrayList<Tipoproducto> tipos = daotipoProducto.listTipoProducto();
        for (Tipoproducto tipo : tipos) {
            valores += tipo + "\n";
        }

        txtTipos.setText(valores);
    }

    public void crearTipo() {
        Tipoproducto tipo = new Tipoproducto();
        tipo.setNombreTipo(txtNombreTipo.getText());
        tipo.setDescripcion(txtDescripcionTipo.getText());

        daotipoProducto.crearTipoProducto(tipo);
        visualizarTipos();
        boxTipoProductos();
    }

    @FXML
    void eliminarTipo(ActionEvent event) {
        daotipoProducto.deleteTipoProducto(Integer.parseInt(txtIdTipo.getText()));
        visualizarTipos();
        boxTipoProductos();
    }

    @FXML
    void modificarTipo(ActionEvent event) {
        daotipoProducto.updateTipoProducto(Integer.parseInt(txtIdTipo.getText()), txtNombreTipo.getText(), txtDescripcionTipo.getText());
        visualizarTipos();
        boxTipoProductos();
    }

    public void boxTipoProductos() {
        cbTipoProducto.getItems().clear();
        ArrayList<Tipoproducto> tipos = daotipoProducto.listTipoProducto();
        for (Tipoproducto tipo : tipos) {
            cbTipoProducto.getItems().add(tipo);
        }
    }

}
