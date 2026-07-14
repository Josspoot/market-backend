package mx.edu.tecdesoftware.market_backend.persistence;

import mx.edu.tecdesoftware.market_backend.domain.Product;
import mx.edu.tecdesoftware.market_backend.domain.repository.ProductRepository;
import mx.edu.tecdesoftware.market_backend.persistence.crud.CompraProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend.persistence.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend.persistence.entity.Producto;
import mx.edu.tecdesoftware.market_backend.persistence.entity.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.List;


@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CompraProductoCrudRepository compraProductoCrudRepository;
    //SELECT * FROM productos
    public List<Product> getAll(){
        // Se "castea" de Iterable a la Lista
       List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
       return productMapper.toProducts(productos);
    }

    public Optional<List<Product>> getByCategory(int categoryId){
        List<Producto> productos =  productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(productos));



    }

    /*
    SELECT * FROM producto
    WHERE cantidad_stock < ?
    AND estado = true;
     */

    public Optional<List<Product>> getScarceProducts(int quantity){
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado( quantity, true);
        return Optional.of(productMapper.toProducts(productos.get()));
    }

    //Obtener un producto dado el id
    public Optional<Product> getProduct(int productId){
        return productoCrudRepository.findById(productId).map(producto -> productMapper.toProduct(producto));

    }

    //Guardar un producto
    public Product save(Product product){
        Producto producto = productMapper.toProducto(product);
        return productMapper.toProduct(productoCrudRepository.save(producto));
    }

    /*
    INSERT INTO producto (nombre, cantidad_stock, estado, id_categoria)
    VALUES (?, ?, ?, ?);
     */

    //Eliminar por id (primero los detalles de compra que lo referencian)
    @Transactional
    public void delete(int productId){
        compraProductoCrudRepository.deleteByIdIdProducto(productId);
        productoCrudRepository.deleteById(productId);
    }
}
