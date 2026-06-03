package mx.edu.tecdesoftware.market_backend.persistence.crud;

import mx.edu.tecdesoftware.market_backend.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    //Obtener los productos escasos
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidad, boolean estado);


}
