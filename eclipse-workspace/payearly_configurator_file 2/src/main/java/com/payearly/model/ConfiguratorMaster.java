package com.payearly.model;

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
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.payearly.enums.CombineDiscountType;
import com.payearly.enums.DiscountType;
import com.payearly.enums.InterestBorneType;
import com.payearly.enums.IntrestComputationType;
import com.payearly.enums.PenalInterestBy;

@Entity
@Table(name = "configurator_master")
public class ConfiguratorMaster extends AbstractAuditingEntity{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    
    @Size(max = 100)
    @Column(name = "program_name", length = 100)
    private String programName;

    @Size(max = 100)
    @Column(name = "program_size", length = 100)
    private String programSize;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "discount_nature", length = 10)
    private DiscountType discountNature;

    @OneToOne
    @JoinColumn(name = "sta_dyn_discount_id")
    private ConfiguratorLookUp staDynDiscount;

    @OneToOne
    @JoinColumn(name = "fee_lookup_id")
    private ConfiguratorLookUp fee;
    
    @Size(max = 100)
    @Enumerated(EnumType.STRING)
    @Column(name = "interest_borne_type", length = 10)
    private InterestBorneType interestBorneType;
    
    @Size(max = 100)
    @Column(name = "source_rate", length = 100)
    private ConfiguratorLookUp sourceRate;
   

    @Size(max = 100)
    @Column(name = "targate_rate", length = 100)
    private ConfiguratorLookUp targateRate;
    
    @Size(max = 100)
    @Column(name = "credit_period_for_source", length = 100)
    private String creditPeriodForSource;
    

    @Size(max = 100)
    @Column(name = "credit_period_for_targate", length = 100)
    private String creditPeriodForTargate;
    
    @Size(max = 100)
    @Enumerated(EnumType.STRING)
    @Column(name = "source_discount", length = 10)
    private DiscountType sourceDiscount;

    @Size(max = 100)
    @Enumerated(EnumType.STRING)
    @Column(name = "targate_discount", length = 10)
    private DiscountType targateDiscount;

    @Size(max = 100)
    @Enumerated(EnumType.STRING)
    @Column(name = "source_targate_comb_discount", length = 100)
    private CombineDiscountType sourceTargateCombDiscount;

    @Enumerated(EnumType.STRING)
    @Size(max = 100)
    @Column(name = "Interest_tranche_credit_days", length = 50)
    private PenalInterestBy InterestTrancheCreditDays;

    @Size(max = 100)
    @Column(name = "penalInterest_id", length = 100)
    private ConfiguratorLookUp penalInterest;
    
    
    @Size(max = 100)
    @Column(name = "disclose_source_rate_target", length = 100)
    private Boolean discloseSourceRateTarget;
    
    @Size(max = 100)
    @Column(name = "disclose_source_rate_source", length = 100)
    private Boolean discloseSourceRateSource;
    
    @Size(max = 100)
    @Column(name = "early_payment_refund", length = 100)
    private String earlyPaymentRefund;

    @Size(max = 100)
    @Column(name = "max_limit", length = 100)
    private String maxLimit;

    @Size(max = 100)
    @Column(name = "min_limit", length = 100)
    private String minLimit;

    @Size(max = 100)
    @Column(name = "min_adhoc_limit", length = 100)
    private String minAdhocLimit;

    @Size(max = 100)
    @Column(name = "max_peak_limit", length = 100)
    private String maxPeakLimit;

    @Size(max = 100)
    @Column(name = "peak_limit_period", length = 100)
    private String peakLimitPeriod;

    @Size(max = 100)
    @Column(name = "adhoc_limit_period", length = 100)
    private String adhocLimitPeriod;

    @Size(max = 100)
    @Column(name = "extension_period", length = 100)
    private String extensionPeriod;

    @Size(max = 100)
    @Column(name = "grace_period", length = 100)
    private String gracePeriod;

    @Size(max = 100)
    @Column(name = "cooling_period", length = 100)
    private String coolingPeriod;

    @Size(max = 100)
    @Column(name = "margin", length = 100)
    private Integer margin;

    @Enumerated(EnumType.STRING)
    @Column(name = "intrestComputationType", length = 100)
    private IntrestComputationType intrestComputationType;

    @OneToOne
    @JoinColumn(name = "guarantee_lookup_id")
    private ConfiguratorLookUp guarantee;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProgramSize() {
		return programSize;
	}

	public void setProgramSize(String programSize) {
		this.programSize = programSize;
	}

	public DiscountType getDiscountNature() {
		return discountNature;
	}

	public void setDiscountNature(DiscountType discountNature) {
		this.discountNature = discountNature;
	}

	public ConfiguratorLookUp getStaDynDiscount() {
		return staDynDiscount;
	}

	public void setStaDynDiscount(ConfiguratorLookUp staDynDiscount) {
		this.staDynDiscount = staDynDiscount;
	}

	public ConfiguratorLookUp getFee() {
		return fee;
	}

	public void setFee(ConfiguratorLookUp fee) {
		this.fee = fee;
	}

	public InterestBorneType getInterestBorneType() {
		return interestBorneType;
	}

	public void setInterestBorneType(InterestBorneType interestBorneType) {
		this.interestBorneType = interestBorneType;
	}

	public ConfiguratorLookUp getSourceRate() {
		return sourceRate;
	}

	public void setSourceRate(ConfiguratorLookUp sourceRate) {
		this.sourceRate = sourceRate;
	}

	public ConfiguratorLookUp getTargateRate() {
		return targateRate;
	}

	public void setTargateRate(ConfiguratorLookUp targateRate) {
		this.targateRate = targateRate;
	}

	public String getCreditPeriodForSource() {
		return creditPeriodForSource;
	}

	public void setCreditPeriodForSource(String creditPeriodForSource) {
		this.creditPeriodForSource = creditPeriodForSource;
	}

	public String getCreditPeriodForTargate() {
		return creditPeriodForTargate;
	}

	public void setCreditPeriodForTargate(String creditPeriodForTargate) {
		this.creditPeriodForTargate = creditPeriodForTargate;
	}

	public DiscountType getSourceDiscount() {
		return sourceDiscount;
	}

	public void setSourceDiscount(DiscountType sourceDiscount) {
		this.sourceDiscount = sourceDiscount;
	}

	public DiscountType getTargateDiscount() {
		return targateDiscount;
	}

	public void setTargateDiscount(DiscountType targateDiscount) {
		this.targateDiscount = targateDiscount;
	}

	public CombineDiscountType getSourceTargateCombDiscount() {
		return sourceTargateCombDiscount;
	}

	public void setSourceTargateCombDiscount(CombineDiscountType sourceTargateCombDiscount) {
		this.sourceTargateCombDiscount = sourceTargateCombDiscount;
	}

	public PenalInterestBy getInterestTrancheCreditDays() {
		return InterestTrancheCreditDays;
	}

	public void setInterestTrancheCreditDays(PenalInterestBy interestTrancheCreditDays) {
		InterestTrancheCreditDays = interestTrancheCreditDays;
	}

	public ConfiguratorLookUp getPenalInterest() {
		return penalInterest;
	}

	public void setPenalInterest(ConfiguratorLookUp penalInterest) {
		this.penalInterest = penalInterest;
	}

	public Boolean getDiscloseSourceRateTarget() {
		return discloseSourceRateTarget;
	}

	public void setDiscloseSourceRateTarget(Boolean discloseSourceRateTarget) {
		this.discloseSourceRateTarget = discloseSourceRateTarget;
	}

	public Boolean getDiscloseSourceRateSource() {
		return discloseSourceRateSource;
	}

	public void setDiscloseSourceRateSource(Boolean discloseSourceRateSource) {
		this.discloseSourceRateSource = discloseSourceRateSource;
	}

	public String getEarlyPaymentRefund() {
		return earlyPaymentRefund;
	}

	public void setEarlyPaymentRefund(String earlyPaymentRefund) {
		this.earlyPaymentRefund = earlyPaymentRefund;
	}

	public String getMaxLimit() {
		return maxLimit;
	}

	public void setMaxLimit(String maxLimit) {
		this.maxLimit = maxLimit;
	}

	public String getMinLimit() {
		return minLimit;
	}

	public void setMinLimit(String minLimit) {
		this.minLimit = minLimit;
	}

	public String getMinAdhocLimit() {
		return minAdhocLimit;
	}

	public void setMinAdhocLimit(String minAdhocLimit) {
		this.minAdhocLimit = minAdhocLimit;
	}

	public String getMaxPeakLimit() {
		return maxPeakLimit;
	}

	public void setMaxPeakLimit(String maxPeakLimit) {
		this.maxPeakLimit = maxPeakLimit;
	}

	public String getPeakLimitPeriod() {
		return peakLimitPeriod;
	}

	public void setPeakLimitPeriod(String peakLimitPeriod) {
		this.peakLimitPeriod = peakLimitPeriod;
	}

	public String getAdhocLimitPeriod() {
		return adhocLimitPeriod;
	}

	public void setAdhocLimitPeriod(String adhocLimitPeriod) {
		this.adhocLimitPeriod = adhocLimitPeriod;
	}

	public String getExtensionPeriod() {
		return extensionPeriod;
	}

	public void setExtensionPeriod(String extensionPeriod) {
		this.extensionPeriod = extensionPeriod;
	}

	public String getGracePeriod() {
		return gracePeriod;
	}

	public void setGracePeriod(String gracePeriod) {
		this.gracePeriod = gracePeriod;
	}

	public String getCoolingPeriod() {
		return coolingPeriod;
	}

	public void setCoolingPeriod(String coolingPeriod) {
		this.coolingPeriod = coolingPeriod;
	}

	public Integer getMargin() {
		return margin;
	}

	public void setMargin(Integer margin) {
		this.margin = margin;
	}

	public IntrestComputationType getIntrestComputationType() {
		return intrestComputationType;
	}

	public void setIntrestComputationType(IntrestComputationType intrestComputationType) {
		this.intrestComputationType = intrestComputationType;
	}

	public ConfiguratorLookUp getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(ConfiguratorLookUp guarantee) {
		this.guarantee = guarantee;
	} 
    
    
}
