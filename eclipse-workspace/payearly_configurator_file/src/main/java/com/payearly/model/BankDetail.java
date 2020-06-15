package com.payearly.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.sun.istack.NotNull;

@Entity()
@Table(name = "bank_detail")
public class BankDetail extends AbstractAuditingEntity {

	private static final long serialVersionUID = 1L;
	
//	@Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//	@Type(type = "uuid-char")
//    @Column(name = "id", updatable = false, nullable = false)
//    private UUID id;
	
	@Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
	
	//@NotBlank
    @NotEmpty
    @Column(name = "bank_name", nullable = false, length = 50)
    private String bankName;

    @Column(name = "account_no", nullable = false, length = 18)
    private Long accountNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private AccountType accountType ;

    @Size(min = 11, max = 11, message = "ifsc code length must be of length 11")
    @NotBlank
    @NotNull
    @Column(name = "ifsc", nullable = false, length = 11)
    private String ifsc;

    @ManyToOne
    @JoinColumn(name = "entity_detail_id")
    private EntityDetails entityDetail;

  
    @NotEmpty
    @Column(name = "bank_address", nullable = false, length = 300)
    private String bankAddress;
    
//    @OneToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "address_id")
//    private EntityAddress address;

    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public EntityDetails getEntityDetail() {
		return entityDetail;
	}

	public void setEntityDetail(EntityDetails entityDetail) {
		this.entityDetail = entityDetail;
	}

//	public EntityAddress getAddress() {
//		return address;
//	}
//
//	public void setAddress(EntityAddress address) {
//		this.address = address;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNo == null) ? 0 : accountNo.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
//		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
//		result = prime * result + ((entityDetail == null) ? 0 : entityDetail.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ifsc == null) ? 0 : ifsc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankDetail other = (BankDetail) obj;
		if (accountNo == null) {
			if (other.accountNo != null)
				return false;
		} else if (!accountNo.equals(other.accountNo))
			return false;
		if (accountType != other.accountType)
			return false;
//		if (address == null) {
//			if (other.address != null)
//				return false;
//		} else if (!address.equals(other.address))
//			return false;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
//		if (entityDetail == null) {
//			if (other.entityDetail != null)
//				return false;
//		} else if (!entityDetail.equals(other.entityDetail))
//			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ifsc == null) {
			if (other.ifsc != null)
				return false;
		} else if (!ifsc.equals(other.ifsc))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BankDetail [id=" + id + ", bankName=" + bankName + ", accountNo=" + accountNo + ", accountType="
				+ accountType + ", ifsc=" + ifsc + ", entityDetail="  + ", address=" +  "]";
	}
    
    
}
