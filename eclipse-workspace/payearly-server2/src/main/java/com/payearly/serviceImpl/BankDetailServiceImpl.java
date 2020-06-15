package com.payearly.serviceImpl;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payearly.domain.BankDetail;
import com.payearly.repository.BankDetailRepository;
import com.payearly.service.BankDetailService;
@Service
@Transactional
public class BankDetailServiceImpl implements BankDetailService {
	
	private final BankDetailRepository bankDetailRepository;

	
	public BankDetailServiceImpl(BankDetailRepository bankDetailRepository) {
		super();
		this.bankDetailRepository = bankDetailRepository;
	}


	@Override
	public BankDetail saveBankDetails(@Valid BankDetail bankDetail) {
		
		return bankDetailRepository.save(bankDetail);
	}
	
	@Override
	public List<BankDetail> getBankDetailsByUUId(@Valid UUID id) {
		return bankDetailRepository.findAllByEntityDetail_id(id);
		
	}


	@Override
	public void deleteBankDetailByUUID(@Valid UUID id) {
		// TODO Auto-generated method stub
		
		bankDetailRepository.deleteById(id);
		
	}


	


}
