package com.payearly.service;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.payearly.domain.BankDetail;

public interface BankDetailService {

	BankDetail saveBankDetails(@Valid BankDetail bankDetail);

	List<BankDetail> getBankDetailsByUUId(@Valid UUID id);

	void deleteBankDetailByUUID(UUID id);

	
	
	
	
	

}
