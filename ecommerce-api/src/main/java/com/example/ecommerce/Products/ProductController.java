package com.example.ecommerce.Products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @PatchMapping(value = "/{id}")
//    public ResponseEntity<Void> updateProduct(@PathVariable Long id,
//                                              @RequestParam (required = false) String name,
//                                              @RequestParam (required = false) String description,
//                                              @RequestParam (required = false) Float price,
//                                              @RequestParam (required = false) Integer quantity){
//        productService.updateProduct(id,name,description,productService,quantity);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @PatchMapping
    public ResponseEntity<Product> updateProduct(@RequestBody UpdateProduct product){
           Product updatedProduct = productService.updateProduct(product);
            return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
    }


}
