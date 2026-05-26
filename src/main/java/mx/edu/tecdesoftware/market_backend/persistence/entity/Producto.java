package mx.edu.tecdesoftware.market_backend.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idproducto;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "id_categoria")
    private String idCategotia;

    @Column(name = "codigo_barras")
    private String codigoBarras;

    @Column(name = "cantidad_stock")
    private Integer cantidadStock;

    @Column(name = "estado")
    private Boolean estado;

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdCategotia() {
        return idCategotia;
    }

    public void setIdCategotia(String idCategotia) {
        this.idCategotia = idCategotia;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Integer getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(Integer cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
