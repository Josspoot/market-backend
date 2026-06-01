package mx.edu.tecdesoftware.market_backend.persistence.entity;
import jakarta.persistence.*;

@Entity
@Table (name = "compras_productos")

public class CompraProducto {

    @EmbeddedId
    private CompraProductoPK id;


    private Integer cantidad;
    private Double total;
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "id_prodcuto", insertable = false, updatable = false)
    private Producto producto;


}
