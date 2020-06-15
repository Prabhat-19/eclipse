package com.payearly.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payearly.domain.Address;
import com.payearly.enums.AddressType;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

    Address findAllByEntityDetail_idAndAddressType(UUID id, AddressType addressType);

    List<Address> findAllByEntityDetail_id(UUID id);

	List<Address> findAllByEntityDetail_idOrderByAddressTypeDesc(UUID id);
    

}
