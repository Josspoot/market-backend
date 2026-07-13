package mx.edu.tecdesoftware.market_backend.persistence;

import mx.edu.tecdesoftware.market_backend.domain.Purchase;
import mx.edu.tecdesoftware.market_backend.domain.repository.PurchaseRepository;
import mx.edu.tecdesoftware.market_backend.persistence.crud.CompraCrudRepository;
import mx.edu.tecdesoftware.market_backend.persistence.entity.Compra;
import mx.edu.tecdesoftware.market_backend.persistence.entity.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper purchaseMapper;

    //SELECT * FROM compras
    @Override
    public List<Purchase> getAll() {
        List<Compra> compras = (List<Compra>) compraCrudRepository.findAll();
        return purchaseMapper.toPurchases(compras);
    }

    //Obtener las compras de un cliente
    @Override
    public Optional<List<Purchase>> getByClient(int clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> purchaseMapper.toPurchases(compras));
    }

    //Guardar una compra con sus productos (cascada)
    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = purchaseMapper.toCompra(purchase);
        // Integridad referencial: cada producto debe referenciar a la compra principal
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return purchaseMapper.toPurchase(compraCrudRepository.save(compra));
    }
}
