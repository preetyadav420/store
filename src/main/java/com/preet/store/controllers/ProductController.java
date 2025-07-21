package com.preet.store.controllers;

import com.preet.store.dto.ProductDto;
import com.preet.store.dto.UserDto;
import com.preet.store.entities.Category;
import com.preet.store.entities.Product;
import com.preet.store.services.CategoryService;
import com.preet.store.services.ProductService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<ProductDto>> getAllProducts(@Parameter(schema = @Schema(type = "Byte")) @RequestParam(required = false) Byte categoryId)
    {
        Iterable<Product> p;
        if(categoryId == null)
            p = productService.getAllProducts();
        else
            p = productService.getAllProductsByCategory(categoryId);

        List<ProductDto> products = new ArrayList<ProductDto>();
        p.forEach(product -> {
            products.add(new ProductDto(product.getId(),product.getName(),product.getDescription(),product.getPrice(),product.getCategory().getId()));
        });
       return ResponseEntity.ok(products::iterator);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id)
    {
        Product product = productService.getProductById(id);
        if(product==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(),product.getCategory().getId()));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto data){
        Product product = new Product();
        product.setName(data.getName());
        product.setPrice(data.getPrice());
        product.setDescription(data.getDescription());

        Category category = categoryService.findById(data.getCategoryId());
        if(category == null)
            return ResponseEntity.badRequest().build();

        product.setCategory(category);
        productService.save(product);

        data.setId(product.getId());
        return ResponseEntity.created(null).body(data);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto data, @PathVariable Long id){

        Product product = productService.getProductById(id);
        if(product == null)
            return ResponseEntity.notFound().build();

        Category category = categoryService.findById(data.getCategoryId());
        if(category == null)
            return ResponseEntity.badRequest().build();

        product.setName(data.getName());
        product.setPrice(data.getPrice());
        product.setDescription(data.getDescription());
        product.setCategory(category);

        productService.save(product);

        data.setId(id);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id)
    {
        Product product = productService.getProductById(id);
        if(product == null)
            return ResponseEntity.notFound().build();

        productService.delete(product);
        return ResponseEntity.noContent().build();
    }

}
