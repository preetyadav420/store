package com.preet.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDto {
    private UUID id ;
    private Set<CartItemDto> items = new HashSet<CartItemDto>();
    private BigDecimal totalPrice = BigDecimal.ZERO;
}
