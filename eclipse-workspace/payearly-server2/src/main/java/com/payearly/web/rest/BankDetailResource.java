package com.payearly.web.rest;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payearly.config.Constants;
import com.payearly.domain.BankDetail;
import com.payearly.service.BankDetailService;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Constants.API_URL)
public class BankDetailResource {

	private final Logger log = LoggerFactory.getLogger(BankDetailResource.class);

	private final BankDetailService bankDetailService;

	public BankDetailResource(BankDetailService bankDetailService) {
		this.bankDetailService = bankDetailService;
	}

	@PostMapping("/save-bank-details")
	public BankDetail saveBankDetails(@Valid @RequestBody BankDetail bankDetail) {
		log.debug("REST request to save bankDetail : {}", bankDetail);

		return bankDetailService.saveBankDetails(bankDetail);

	}

	@GetMapping(path = "/get-bank-details/{id}")
	public List<BankDetail> getBankDetails(@PathVariable UUID id) {
		log.debug("REST request for getting bankDetail : {}", id);

		return bankDetailService.getBankDetailsByUUId(id);

	}

	@DeleteMapping(path = "/delete-bank-details/{id}")
	public void deleteBankDetails(@PathVariable UUID id) {
		log.debug("REST request for deleting bankDetail : {}", id);

		bankDetailService.deleteBankDetailByUUID(id);

	}
}
