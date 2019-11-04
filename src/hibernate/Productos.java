package hibernate;
// Generated 27/10/2019 12:42:01 PM by Hibernate Tools 4.3.1

import DAO.DAORiego;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Productos generated by hbm2java
 */
public class Productos implements java.io.Serializable {

    @Id
    @Column(name = "id")
    private int id;
    private String nombre;
    private Integer precio;
    private Integer tipoProducto;
    private Integer estadoProducto;
    private Date fechaIngreso;
    private ArrayList<Riegos> riegos;

    public Productos() {
        SetRiegos();
    }

    @Override
    public String toString() {
        return "Productos{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", tipoProducto=" + tipoProducto + ", estadoProducto=" + estadoProducto + ", fechaIngreso=" + fechaIngreso + ", riegos=" + riegos + '}' + "\n";
    }

    public Productos(int id) {
        this.id = id;
        SetRiegos();
    }

    public Productos(int id, String nombre, Integer precio, Integer tipoProducto, Integer estadoProducto, Date fechaIngreso) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tipoProducto = tipoProducto;
        this.estadoProducto = estadoProducto;
        this.fechaIngreso = fechaIngreso;
        SetRiegos();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return this.precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getTipoProducto() {
        return this.tipoProducto;
    }

    public void setTipoProducto(Integer tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public Integer getEstadoProducto() {
        return this.estadoProducto;
    }

    public void setEstadoProducto(Integer estadoProducto) {
        this.estadoProducto = estadoProducto;
    }

    public Date getFechaIngreso() {
        return this.fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setRiego(Riegos riego) {
        riegos.add(riego);
    }

    public ArrayList<Riegos> getRiegos() {
        return riegos;
    }

    public void SetRiegos() {
        DAORiego daoriego = new DAORiego();
        this.riegos = daoriego.listRiegosByProduct(this.id);
    }

}