package com.payearly.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.payearly.enums.CombineDiscountType;
import com.payearly.enums.DiscountType;
import com.payearly.enums.InterestBorneType;
import com.payearly.enums.IntrestComputationType;
import com.payearly.enums.PenalInterestBy;

import lombok.Data;

@Data
@Entity
@Table(name = "configurator_master")
public class ConfiguratorMaster extends AbstractAuditingEntity{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "program_name", length = 100)
    private String programName;

    @Column(name = "program_size", length = 100)
    private String programSize;

    @Enumerated(EnumType.STRING)
    @Column(name = "discounting_nature", length = 10)
    private DiscountType discountingNature;

    @OneToOne
    @JoinColumn(name = "sta_dyn_discount_detail_id")
    private ConfiguratorLookUpDetail staDynDiscount;

    @Column(name = "discounting_value")
    private Float discounting_value;
    
    @OneToOne
    @JoinColumn(name = "fee_lookup_detail_id")
    private ConfiguratorLookUpDetail fee;
    
    @Column(name = "fee_value")
    private Float fee_value;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "interest_borne_type", length = 10)
    private InterestBorneType interestBorneType;
    
    @OneToOne
    @JoinColumn(name = "source_rate_detail_id")
    private ConfiguratorLookUpDetail sourceRate;
   

    @Column(name = "source_rate_value")
    private Float sourceRateValue;
    
    @OneToOne
    @JoinColumn(name = "targate_rate_detail_id")
    private ConfiguratorLookUpDetail targateRate;
    
    @Column(name = "targate_rate_value")
    private Float targateRateValue;
    
    @Column(name = "credit_period_for_source", length = 100)
    private String creditPeriodForSource;
    

    @Column(name = "credit_period_for_targate", length = 100)
    private String creditPeriodForTargate;

    @Enumerated(EnumType.STRING)
    @Column(name = "source_discount", length = 10)
    private DiscountType sourceDiscount;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "targate_discount", length = 10)
    private DiscountType targateDiscount;

    @Enumerated(EnumType.STRING)
    private CombineDiscountType sourceTargateCombDiscount;

    @Enumerated(EnumType.STRING)
    @Column(name = "interest_tranche_credit_days", length = 50)
    private PenalInterestBy InterestTrancheCreditDays;

    @OneToOne
    @JoinColumn(name = "penal_interest_detail_id")
    private ConfiguratorLookUpDetail penalInterest;
    
    @Column(name = "penal_interest_value")
    private Float penalInterestValue;
    
    @Column(name = "disclose_source_rate_target", length = 100)
    private Boolean discloseSourceRateTarget;
    
    @Column(name = "disclose_source_rate_source", length = 100)
    private Boolean discloseSourceRateSource;
    
    
    @Column(name = "early_payment_refund", length = 100)
    private PenalInterestBy earlyPaymentRefund;

    @Column(name = "max_limit", length = 100)
    private String maxLimit;

    @Column(name = "min_limit", length = 100)
    private String minLimit;

    @Column(name = "min_adhoc_limit", length = 100)
    private String minAdhocLimit;

    @Column(name = "max_peak_limit", length = 100)
    private String maxPeakLimit;

    @Column(name = "peak_limit_period", length = 100)
    private String peakLimitPeriod;

    @Column(name = "adhoc_limit_period", length = 100)
    private String adhocLimitPeriod;

    @Column(name = "extension_period", length = 100)
    private String extensionPeriod;

    @Column(name = "grace_period", length = 100)
    private String gracePeriod;

    @Column(name = "cooling_period", length = 100)
    private String coolingPeriod;

    @Column(name = "margin", length = 100)
    private Integer margin;

    @Enumerated(EnumType.STRING)
    @Column(name = "intrest_computation_type", length = 100)
    private IntrestComputationType intrestComputationType;

    @OneToOne
    @JoinColumn(name = "guarantee_lookup_detail_id")
    private ConfiguratorLookUpDetail guarantee;

    @Column(name = "guarantee_value")
    private Float guaranteeValue;
}
