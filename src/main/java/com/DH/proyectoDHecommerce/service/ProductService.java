package com.DH.proyectoDHecommerce.service;

import com.DH.proyectoDHecommerce.model.Product;
import com.DH.proyectoDHecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void processOrder(Long productId, Integer orderedQuantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto"));

        Integer currentStock = product.getInStock();
        if (currentStock >= orderedQuantity) {
            product.setInStock(currentStock - orderedQuantity);
            productRepository.save(product);
        } else {
            throw new RuntimeException("Sin stock para el producto: " + product.getTitle());
        }}

    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    public int getProductStockById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return product.getInStock();
    }

    public void updateProductStock(Long id, int newStock) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setInStock(newStock);
        productRepository.save(product);
    }
}
