package com.preet.store.services;

import com.preet.store.entities.Category;
import com.preet.store.entities.Product;
import com.preet.store.repositories.CategoryRepository;
import com.preet.store.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@AllArgsConstructor
public class ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public Iterable<Product> getAllProductsByCategory(Byte categoryId) {

        return productRepository.findByCategoryId(categoryId);

    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

//    @Transactional
//    public void saveProduct(Product product)
//    {
//        Category category = categoryRepository.findById(2L).orElseThrow();
//
//        product.setCategory(category);
//        category.getProducts().add(product);
//        productRepository.save(product);
//    }
//
//    public void getProductsInRange(BigDecimal min, BigDecimal max)
//    {
//        List<Product> products =  productRepository.findProductByPriceBetween(min, max);
//        products.forEach(p->{
//            System.out.println(p);
//        });
//
//    }
//
//    public void getProducts()
//    {
//        productRepository.findProductByPriceIsNotNull().forEach(e->{
//            System.out.println(e);
//        });
//    }
//
//    public void getProductsByPrice(BigDecimal price)
//    {
//        List<Product> products =  productRepository.findProduct(price);
//        products.forEach(p->{
//            System.out.println(p);
//        });
//    }
//
//    public void deleteProduct(Product product)
//    {
//        productRepository.deleteById(2L);
//    }
}
