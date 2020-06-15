package com.payearly.serviceImpl;

import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.payearly.domain.Address;
import com.payearly.repository.AddressRepository;
import com.payearly.service.AddressService;


@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    
    
    public AddressServiceImpl(AddressRepository addressRepository) {
        super();
        this.addressRepository = addressRepository;
    }

    @Override
    public Address getAddress(UUID id) {

        return addressRepository.findById(id).get();
    }

    @Override
    public Address createAddress(@Valid Address address) {

        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(@Valid Address address) {

        return addressRepository.save(address);
    }

    @Override
    public Page<Address> getAddress(Pageable pageable) {

        return addressRepository.findAll(pageable);
    }

}
