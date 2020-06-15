package com.payearly.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.payearly.enums.CombineDiscountType;
import com.payearly.enums.DiscountType;
import com.payearly.enums.InterestBorneType;
import com.payearly.enums.IntrestComputationType;
import com.payearly.enums.PenalInterestBy;
import com.payearly.enums.ScoreCardType;

import lombok.Data;

@Data
@Entity
@Table(name = "configurator_child")
public class ConfiguratorChild  extends AbstractAuditingEntity {

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

    @Enumerated(EnumType.STRING)
    @Column(name = "interest_borne_type", length = 10)
    private InterestBorneType interestBorneType;
    
    @Column(name = "credit_period_for_source", length = 100)
    private String creditPeriodForSource;

    @Column(name = "credit_period_for_targate", length = 100)
    private String creditPeriodForTargate;
    
    @Column(name = "credit_period_for_all", length = 100)
    private String creditPeriodForAll;

    @Enumerated(EnumType.STRING)
    @Column(name = "source_discount", length = 10)
    private DiscountType sourceDiscount;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "targate_discount", length = 10)
    private DiscountType targateDiscount;

    @Column(name = "source_targate_comb_discount")
    private CombineDiscountType sourceTargateCombDiscount;

    @Enumerated(EnumType.STRING)
    @Column(name = "panel_Interest_by", length = 50)
    private PenalInterestBy panelInterestBy;

    @Column(name = "disclose_source_rate_target", length = 100)
    private Boolean discloseSourceRateTarget;
    
    @Column(name = "disclose_source_rate_source", length = 100)
    private Boolean discloseSourceRateSource;

    @Column(name = "early_payment_refund", length = 100)
    private PenalInterestBy earlyPaymentRefund;

    @Column(name = "interest_tranche_credit_days", length = 100)
    private String InterestTrancheCreditDays;

    @Column(name = "max_limit", length = 100)
    private String maxLimit;

    @Column(name = "min_limit", length = 100)
    private String minLimit;

    @Column(name = "max_adhoc_limit", length = 100)
    private String maxAdhocLimit;

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
    private Float margin;

    @Enumerated(EnumType.STRING)
    @Column(name = "intrest_computation_type", length = 100)
    private IntrestComputationType intrestComputationType;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "configurator_type" , length = 50, nullable = false)
    private ScoreCardType configuratorType;
    
    @OneToOne
    @JoinColumn(name = "entity_category_id")
    private EntityCategory entityCategory;

    @OneToMany(mappedBy = "configuratorChild", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<EntityGuarantee> entityGuarantee = new HashSet<EntityGuarantee>();

}
