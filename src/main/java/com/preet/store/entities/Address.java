package com.preet.store.entities;

import jakarta.persistence.*;
import lombok.*;

//@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "zipcode",nullable = false)
    private String zipcode;

    @ManyToOne(fetch = FetchType.LAZY)
    //@ToString.Exclude
    @JoinColumn(name = "user_id" )
    private User user;
}
