package com.DH.proyectoDHecommerce.controller;


import com.DH.proyectoDHecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}/stock")
    public int getProductStock(@PathVariable Long id) {
        return productService.getProductStockById(id);
    }

    @PutMapping("/{id}/updateStock")
    public void updateProductStock(@PathVariable Long id, @RequestParam int newStock) {
        productService.updateProductStock(id, newStock);
    }

    @PostMapping("/{productId}/order")
    public ResponseEntity<String> processOrder(@PathVariable Long productId,
                                               @RequestParam Integer orderedQuantity) {
        try {
            productService.processOrder(productId, orderedQuantity);
            return ResponseEntity.ok("La orden fue cargada éxitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
