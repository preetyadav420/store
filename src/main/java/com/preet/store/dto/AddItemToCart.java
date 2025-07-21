package com.preet.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link com.preet.store.entities.CatItem}
 */
@AllArgsConstructor
@Getter
public class AddItemToCart implements Serializable {
    private final ProductDto1 product;
}