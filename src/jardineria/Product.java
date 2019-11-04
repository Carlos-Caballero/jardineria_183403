/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jardineria;

import java.sql.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Adrian Lopez
 */
public class Product {
    private SimpleIntegerProperty id;
    private SimpleStringProperty nombre;
    private SimpleIntegerProperty precio;
    private SimpleStringProperty tipoProducto;
    private SimpleStringProperty estadoProducto;
    private SimpleStringProperty fechaIngreso;
    
    public Product(int id, String nombre, int precio, String tipoProducto, String estadoProducto, Date fechaIngreso){
            this.id = new SimpleIntegerProperty(id);
            this.nombre = new SimpleStringProperty(nombre);
            this.precio = new SimpleIntegerProperty(precio);
            this.tipoProducto = new SimpleStringProperty(tipoProducto);
            this.estadoProducto = new SimpleStringProperty(estadoProducto);
            this.fechaIngreso = new SimpleStringProperty(fechaIngreso.toString());
        }
    
    public void setId(int id) {
		this.id.set(id);
	}
    
    public int getId() {
            return id.get();
	}
    
    public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}
    
    public String getNombre() {
            return nombre.get();
	}
    
    public void setPrecio(int precio) {
		this.precio.set(precio);
	}
    
    public int getPrecio() {
            return precio.get();
	}
    
    public void setTipoProducto(String tipoProducto) {
		this.tipoProducto.set(tipoProducto);
	}
    
    public String getTipoProducto() {
            return tipoProducto.get();
	}
    
    public void setEstadoProducto(String estadoProducto) {
		this.estadoProducto.set(estadoProducto);
	}
    
    public String getEstadoProducto() {
            return estadoProducto.get();
	}
    
    public void setfechaIngreso(Date fechaIngreso) {
		this.fechaIngreso.set(fechaIngreso.toString());
	}
    
    public String getFechaIngreso() {
            return fechaIngreso.get();
	}
    
    @Override
	public String toString(){
		return nombre+"\t"+fechaIngreso;
	}
}
