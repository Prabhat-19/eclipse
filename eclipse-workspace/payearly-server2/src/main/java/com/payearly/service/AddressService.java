package com.payearly.service;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.payearly.domain.Address;

public interface AddressService {

    Address getAddress(UUID id);

    Address createAddress(@Valid Address address);

    Address updateAddress(@Valid Address address);

    Page<Address> getAddress(Pageable pageable);

}
