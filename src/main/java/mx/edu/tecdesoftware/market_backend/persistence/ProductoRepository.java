package mx.edu.tecdesoftware.market_backend.persistence;

import mx.edu.tecdesoftware.market_backend.persistence.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend.persistence.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

import java.util.List;

public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    //SELECT * FROM productos
    public List<Producto> getAll(){
        // Se "castea" de Iterable a la Lista
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public List<Producto> getByCategoria(int idCategoria){
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    /*
    SELECT * FROM producto
    WHERE cantidad_stock < ?
    AND estado = true;
     */

    public Optional<List<Producto>> getEscasos(int cantidad){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado( cantidad, true);
    }

    //Obtener un producto dado el id
    public Optional<Producto> getProductoById(int idProducto){
        return productoCrudRepository.findById(idProducto);
    }

    //Guardar un producto
    public Producto save(Producto producto){
        return productoCrudRepository.save(producto);
    }

    /*
    INSERT INTO producto (nombre, cantidad_stock, estado, id_categoria)
    VALUES (?, ?, ?, ?);
     */

    //Eliminar por id
    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }
}
