package com.example.ecommerce.Products;

import com.example.ecommerce.CustomExceptions.ProductNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    // todo: this sorting might not be worth the performance hit...
    public List<Product> getAllProducts(){
        return productRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
    }

    public Product getProductById(Long id) {
       return productRepository.findById(id)
               .orElseThrow(()->new ProductNotFoundException(String.format("Product with id:%s does not exist",id)));
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public Product updateProduct(UpdateProductDTO updatedProduct) {
        Product targetProduct = productRepository.findById(updatedProduct.getId())
                .orElseThrow(()->new IllegalArgumentException("product with id: " + updatedProduct.getId() + " does not exist"));

       targetProduct.setName(updatedProduct.getName() != null ? updatedProduct.getName() : targetProduct.getName());
       targetProduct.setDescription(updatedProduct.getDescription() != null ? updatedProduct.getDescription() : targetProduct.getDescription());
       targetProduct.setPrice(updatedProduct.getPrice() != null ? updatedProduct.getPrice() : targetProduct.getPrice());
       targetProduct.setQuantity(updatedProduct.getQuantity() != null ? updatedProduct.getQuantity() : targetProduct.getQuantity());

       return productRepository.save(targetProduct);
    }
}
