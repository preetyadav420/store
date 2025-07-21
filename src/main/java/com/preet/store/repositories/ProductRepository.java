package com.preet.store.repositories;

import com.preet.store.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Long> {
    Iterable<Product> findByCategoryId(Byte categoryId);

//    List<Product> findProductByPriceBetween(BigDecimal min, BigDecimal max);
//
//    @Query(value = "Select id,  name from products", nativeQuery = true)
//    List<ProductSummaryDto> findProductByPriceIsNotNull();
//
//    @Query(value = "Select * from products where products.price = :price", nativeQuery = true)
//    List<Product> findProduct(@Param("price") BigDecimal price);
}
