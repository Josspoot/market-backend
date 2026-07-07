package mx.edu.tecdesoftware.market_backend.web.controller;

import mx.edu.tecdesoftware.market_backend.domain.Product;
import mx.edu.tecdesoftware.market_backend.domain.repository.ProductRepository;
import mx.edu.tecdesoftware.market_backend.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int productId){
        return productService.getProduct(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Product> getCategory(@PathVariable("categoryId") int  categoryId){
        return productService.getProduct(categoryId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping("")
    public ResponseEntity<Product> save(@RequestBody Product product){
        return  ResponseEntity.ok(productService.save(product));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("productId") int productId){
        return ResponseEntity.ok(productService.delete(productId));
    }


}
