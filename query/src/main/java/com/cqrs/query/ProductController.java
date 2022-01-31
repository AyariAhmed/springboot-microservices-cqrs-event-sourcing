package com.cqrs.query;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/query")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public List<ProductEntity> getProducts(){
        return this.productService.getProducts();
    }

    @GetMapping("/{ref}")
    public ProductEntity getProductByRef(@PathVariable String ref){
        return this.productService.getProductByRef(ref);
    }

    @DeleteMapping("/purge")
    public List<ProductEntity> purgeDb(){
        return this.productService.purgeDb();
    }
}
