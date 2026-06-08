package mx.edu.tecdesoftware.market_backend.persistence.entity.mapper;

import mx.edu.tecdesoftware.market_backend.domain.Product;
import mx.edu.tecdesoftware.market_backend.persistence.entity.Producto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import java.util.List;

@Mapper(componentModel = "spring", uses= CategoryMapper.class)
public interface ProductMapper {
    @Mappings({
            @Mapping(source="idProducto", target  = "productId"),
            @Mapping(source="nombre", target  = "name"),
            @Mapping(source="precioVenta", target  = "price"),
            @Mapping(source="cantidadStock", target  = "stock"),
            @Mapping(source="categoria", target  = "category"),
    })
    Product toProduct(Producto producto);
    List<Product> toProducts(List<Producto> productos);

    @InheritConfiguration
    @Mapping(target ="codigoBarras", ignore = true)
    Producto toProducto(Product product);
}
