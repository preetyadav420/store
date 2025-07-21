package com.preet.store.services;

import com.preet.store.entities.Address;
import com.preet.store.repositories.AddressRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressService {
    AddressRepository addressRepository;

    //@Transactional
    public void getAddress(Long id)
    {
        Address address = addressRepository.findById(id).orElseThrow();
        System.out.println(address);
    }
}
