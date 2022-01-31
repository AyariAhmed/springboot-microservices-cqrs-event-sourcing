package com.cqrs.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/command")
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public String createProduct(@RequestBody ProductEntity product) throws JsonProcessingException {
        return this.productService.createProduct(product);
    }

    @PostMapping("/buy/{ref}")
    public String buyProduct(@PathVariable String ref) throws JsonProcessingException {
        return this.productService.buyProduct(ref);
    }

    @PostMapping("/refill/{ref}")
    public String refillProduct(@PathVariable String ref, @RequestParam(name="quantity", defaultValue = "0") int quantity) throws JsonProcessingException {
        return this.productService.refillProduct(ref, quantity);
    }

    @GetMapping("/")
    public List<ProductEntity> getAll() {
        return this.productService.getProducts();
    }

    @DeleteMapping("/purge")
    public List<ProductEntity> purgeDb(){
        return this.productService.purgeDb();
    }
}
