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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.payearly.enums.CombineDiscountType;
import com.payearly.enums.DiscountType;
import com.payearly.enums.InterestBorneType;
import com.payearly.enums.IntrestComputationType;
import com.payearly.enums.PenalInterestBy;

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
    @Column(name = "discount_nature", length = 10)
    private DiscountType discountNature;
    

    @OneToOne
    @JoinColumn(name = "sta_dyn_discount_detail_id")
    private ConfiguratorLookUpDetail staDynDiscount;

    @OneToOne
    @JoinColumn(name = "fee_lookup_detail_id")
    private ConfiguratorLookUpDetail fee;
    

    @Enumerated(EnumType.STRING)
    @Column(name = "interest_borne_type", length = 10)
    private InterestBorneType interestBorneType;
    
    @OneToOne
    @JoinColumn(name = "source_rate_detail_id")
    private ConfiguratorLookUpDetail sourceRate;
   
    @OneToOne
    @JoinColumn(name = "targate_rate_detail_id")
    private ConfiguratorLookUpDetail targateRate;
    
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

    @Column(name = "source_targate_comb_discount")
    private CombineDiscountType sourceTargateCombDiscount;

    @Enumerated(EnumType.STRING)
    @Column(name = "interest_tranche_credit_days", length = 50)
    private PenalInterestBy InterestTrancheCreditDays;

    @OneToOne
    @JoinColumn(name = "penal_interest_detail_id")
    private ConfiguratorLookUpDetail penalInterest;
    
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

	public PenalInterestBy getEarlyPaymentRefund() {
		return earlyPaymentRefund;
	}

	public void setEarlyPaymentRefund(PenalInterestBy earlyPaymentRefund) {
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

	public ConfiguratorLookUpDetail getStaDynDiscount() {
		return staDynDiscount;
	}

	public void setStaDynDiscount(ConfiguratorLookUpDetail staDynDiscount) {
		this.staDynDiscount = staDynDiscount;
	}

	public ConfiguratorLookUpDetail getFee() {
		return fee;
	}

	public void setFee(ConfiguratorLookUpDetail fee) {
		this.fee = fee;
	}

	public InterestBorneType getInterestBorneType() {
		return interestBorneType;
	}

	public void setInterestBorneType(InterestBorneType interestBorneType) {
		this.interestBorneType = interestBorneType;
	}

	public ConfiguratorLookUpDetail getSourceRate() {
		return sourceRate;
	}

	public void setSourceRate(ConfiguratorLookUpDetail sourceRate) {
		this.sourceRate = sourceRate;
	}

	public ConfiguratorLookUpDetail getTargateRate() {
		return targateRate;
	}

	public void setTargateRate(ConfiguratorLookUpDetail targateRate) {
		this.targateRate = targateRate;
	}

	public ConfiguratorLookUpDetail getPenalInterest() {
		return penalInterest;
	}

	public void setPenalInterest(ConfiguratorLookUpDetail penalInterest) {
		this.penalInterest = penalInterest;
	}

	public ConfiguratorLookUpDetail getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(ConfiguratorLookUpDetail guarantee) {
		this.guarantee = guarantee;
	}

	@Override
	public String toString() {
		return "ConfiguratorChild [id=" + id + ", programName=" + programName + ", programSize=" + programSize
				+ ", discountNature=" + discountNature + ", staDynDiscount=" + staDynDiscount + ", fee=" + fee
				+ ", interestBorneType=" + interestBorneType + ", sourceRate=" + sourceRate + ", targateRate="
				+ targateRate + ", creditPeriodForSource=" + creditPeriodForSource + ", creditPeriodForTargate="
				+ creditPeriodForTargate + ", sourceDiscount=" + sourceDiscount + ", targateDiscount=" + targateDiscount
				+ ", sourceTargateCombDiscount=" + sourceTargateCombDiscount + ", InterestTrancheCreditDays="
				+ InterestTrancheCreditDays + ", penalInterest=" + penalInterest + ", discloseSourceRateTarget="
				+ discloseSourceRateTarget + ", discloseSourceRateSource=" + discloseSourceRateSource
				+ ", earlyPaymentRefund=" + earlyPaymentRefund + ", maxLimit=" + maxLimit + ", minLimit=" + minLimit
				+ ", minAdhocLimit=" + minAdhocLimit + ", maxPeakLimit=" + maxPeakLimit + ", peakLimitPeriod="
				+ peakLimitPeriod + ", adhocLimitPeriod=" + adhocLimitPeriod + ", extensionPeriod=" + extensionPeriod
				+ ", gracePeriod=" + gracePeriod + ", coolingPeriod=" + coolingPeriod + ", margin=" + margin
				+ ", intrestComputationType=" + intrestComputationType + ", guarantee=" + guarantee + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((InterestTrancheCreditDays == null) ? 0 : InterestTrancheCreditDays.hashCode());
		result = prime * result + ((adhocLimitPeriod == null) ? 0 : adhocLimitPeriod.hashCode());
		result = prime * result + ((coolingPeriod == null) ? 0 : coolingPeriod.hashCode());
		result = prime * result + ((creditPeriodForSource == null) ? 0 : creditPeriodForSource.hashCode());
		result = prime * result + ((creditPeriodForTargate == null) ? 0 : creditPeriodForTargate.hashCode());
		result = prime * result + ((discloseSourceRateSource == null) ? 0 : discloseSourceRateSource.hashCode());
		result = prime * result + ((discloseSourceRateTarget == null) ? 0 : discloseSourceRateTarget.hashCode());
		result = prime * result + ((discountNature == null) ? 0 : discountNature.hashCode());
		result = prime * result + ((earlyPaymentRefund == null) ? 0 : earlyPaymentRefund.hashCode());
		result = prime * result + ((extensionPeriod == null) ? 0 : extensionPeriod.hashCode());
		result = prime * result + ((fee == null) ? 0 : fee.hashCode());
		result = prime * result + ((gracePeriod == null) ? 0 : gracePeriod.hashCode());
		result = prime * result + ((guarantee == null) ? 0 : guarantee.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((interestBorneType == null) ? 0 : interestBorneType.hashCode());
		result = prime * result + ((intrestComputationType == null) ? 0 : intrestComputationType.hashCode());
		result = prime * result + ((margin == null) ? 0 : margin.hashCode());
		result = prime * result + ((maxLimit == null) ? 0 : maxLimit.hashCode());
		result = prime * result + ((maxPeakLimit == null) ? 0 : maxPeakLimit.hashCode());
		result = prime * result + ((minAdhocLimit == null) ? 0 : minAdhocLimit.hashCode());
		result = prime * result + ((minLimit == null) ? 0 : minLimit.hashCode());
		result = prime * result + ((peakLimitPeriod == null) ? 0 : peakLimitPeriod.hashCode());
		result = prime * result + ((penalInterest == null) ? 0 : penalInterest.hashCode());
		result = prime * result + ((programName == null) ? 0 : programName.hashCode());
		result = prime * result + ((programSize == null) ? 0 : programSize.hashCode());
		result = prime * result + ((sourceDiscount == null) ? 0 : sourceDiscount.hashCode());
		result = prime * result + ((sourceRate == null) ? 0 : sourceRate.hashCode());
		result = prime * result + ((sourceTargateCombDiscount == null) ? 0 : sourceTargateCombDiscount.hashCode());
		result = prime * result + ((staDynDiscount == null) ? 0 : staDynDiscount.hashCode());
		result = prime * result + ((targateDiscount == null) ? 0 : targateDiscount.hashCode());
		result = prime * result + ((targateRate == null) ? 0 : targateRate.hashCode());
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
		ConfiguratorChild other = (ConfiguratorChild) obj;
		if (InterestTrancheCreditDays != other.InterestTrancheCreditDays)
			return false;
		if (adhocLimitPeriod == null) {
			if (other.adhocLimitPeriod != null)
				return false;
		} else if (!adhocLimitPeriod.equals(other.adhocLimitPeriod))
			return false;
		if (coolingPeriod == null) {
			if (other.coolingPeriod != null)
				return false;
		} else if (!coolingPeriod.equals(other.coolingPeriod))
			return false;
		if (creditPeriodForSource == null) {
			if (other.creditPeriodForSource != null)
				return false;
		} else if (!creditPeriodForSource.equals(other.creditPeriodForSource))
			return false;
		if (creditPeriodForTargate == null) {
			if (other.creditPeriodForTargate != null)
				return false;
		} else if (!creditPeriodForTargate.equals(other.creditPeriodForTargate))
			return false;
		if (discloseSourceRateSource == null) {
			if (other.discloseSourceRateSource != null)
				return false;
		} else if (!discloseSourceRateSource.equals(other.discloseSourceRateSource))
			return false;
		if (discloseSourceRateTarget == null) {
			if (other.discloseSourceRateTarget != null)
				return false;
		} else if (!discloseSourceRateTarget.equals(other.discloseSourceRateTarget))
			return false;
		if (discountNature != other.discountNature)
			return false;
		if (earlyPaymentRefund != other.earlyPaymentRefund)
			return false;
		if (extensionPeriod == null) {
			if (other.extensionPeriod != null)
				return false;
		} else if (!extensionPeriod.equals(other.extensionPeriod))
			return false;
		if (fee == null) {
			if (other.fee != null)
				return false;
		} else if (!fee.equals(other.fee))
			return false;
		if (gracePeriod == null) {
			if (other.gracePeriod != null)
				return false;
		} else if (!gracePeriod.equals(other.gracePeriod))
			return false;
		if (guarantee == null) {
			if (other.guarantee != null)
				return false;
		} else if (!guarantee.equals(other.guarantee))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (interestBorneType != other.interestBorneType)
			return false;
		if (intrestComputationType != other.intrestComputationType)
			return false;
		if (margin == null) {
			if (other.margin != null)
				return false;
		} else if (!margin.equals(other.margin))
			return false;
		if (maxLimit == null) {
			if (other.maxLimit != null)
				return false;
		} else if (!maxLimit.equals(other.maxLimit))
			return false;
		if (maxPeakLimit == null) {
			if (other.maxPeakLimit != null)
				return false;
		} else if (!maxPeakLimit.equals(other.maxPeakLimit))
			return false;
		if (minAdhocLimit == null) {
			if (other.minAdhocLimit != null)
				return false;
		} else if (!minAdhocLimit.equals(other.minAdhocLimit))
			return false;
		if (minLimit == null) {
			if (other.minLimit != null)
				return false;
		} else if (!minLimit.equals(other.minLimit))
			return false;
		if (peakLimitPeriod == null) {
			if (other.peakLimitPeriod != null)
				return false;
		} else if (!peakLimitPeriod.equals(other.peakLimitPeriod))
			return false;
		if (penalInterest == null) {
			if (other.penalInterest != null)
				return false;
		} else if (!penalInterest.equals(other.penalInterest))
			return false;
		if (programName == null) {
			if (other.programName != null)
				return false;
		} else if (!programName.equals(other.programName))
			return false;
		if (programSize == null) {
			if (other.programSize != null)
				return false;
		} else if (!programSize.equals(other.programSize))
			return false;
		if (sourceDiscount != other.sourceDiscount)
			return false;
		if (sourceRate == null) {
			if (other.sourceRate != null)
				return false;
		} else if (!sourceRate.equals(other.sourceRate))
			return false;
		if (sourceTargateCombDiscount != other.sourceTargateCombDiscount)
			return false;
		if (staDynDiscount == null) {
			if (other.staDynDiscount != null)
				return false;
		} else if (!staDynDiscount.equals(other.staDynDiscount))
			return false;
		if (targateDiscount != other.targateDiscount)
			return false;
		if (targateRate == null) {
			if (other.targateRate != null)
				return false;
		} else if (!targateRate.equals(other.targateRate))
			return false;
		return true;
	}
    
    
}
