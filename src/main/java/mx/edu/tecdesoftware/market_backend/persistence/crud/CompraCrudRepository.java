package mx.edu.tecdesoftware.market_backend.persistence.crud;

import mx.edu.tecdesoftware.market_backend.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {

    //Obtener las compras de un cliente
    Optional<List<Compra>> findByIdCliente(String idCliente);
}
