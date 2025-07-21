package com.preet.store.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

//@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST} )
    @JoinTable(
            name = "user_tag",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @ToString.Exclude
    private Set<Tag> tags = new HashSet<Tag>();


    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    //@ToString.Exclude
    private Set<Address> addresses = new HashSet<Address>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    @JoinTable(
            name = "wishlist",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    //@ToString.Exclude
    private Set<Product> products = new HashSet<Product>();

    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    //@ToString.Exclude
    private Profile profile;

    public void addAddress(Address address)
    {
        address.setUser(this);
        addresses.add(address);
    }

    public void removeAddress(Address address)
    {
        addresses.remove(address);
        address.setUser(null);
    }

    public void addTag(String tagName)
    {
        Tag tag = new Tag();
        tag.setName(tagName);
        tags.add(tag);
        tag.getUsers().add(this);
    }

    public void removeTag(Tag tag)
    {
        tags.remove(tag);
        tag.getUsers().remove(this);
    }

    public void addProduct(Product product)
    {
        products.add(product);
        product.getUsers().add(this);
    }

    public void removeProduct(Product product)
    {
        products.remove(product);
        product.getUsers().remove(this);
    }

    public void setProfile(Profile profile)
    {
        this.profile = profile;
        profile.setUser(this);
    }

    public void removeProfile(Profile profile)
    {
        this.profile = null;
        profile.setUser(null);
    }

}
