package com.payearly.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.payearly.enums.AccountType;

import lombok.Data;

@Data
@Entity
@Table(name = "bank_detail")
public class BankDetail extends AbstractAuditingEntity {

	private static final long serialVersionUID = 3732255572153196654L;

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id; 

    @Column(name = "bank_name", nullable = false, length = 50)
    private String bankName;

    @Column(name = "account_no", nullable = false, length = 18)
    private Long accountNo;

    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    @Size(min = 11, max = 11, message = "ifsc code length must be of length 11")
    @Column(name = "ifsc", nullable = false, length = 11)
    private String ifsc;

    @Column(name = "bank_address", nullable = false, length = 255)
    private String bankAddress;
    
    @ManyToOne
    @JoinColumn(name = "entity_detail_id")
    private EntityDetail entityDetail;

//    @OneToOne(fetch = FetchType.LAZY, optional = true)
//    @JoinColumn(name = "address_id", nullable = true, foreignKey = @ForeignKey(foreignKeyDefinition ="", name ="fk_address_bank_detail"))
//    private Address address;
}
