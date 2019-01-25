package ch.heigvd.amt.wp2.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "rule_point_scale_amount")
public class RulePointScaleAmountEntity extends AbstractDomainModelEntity<Long> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rule_id", nullable = true)
    private RuleEntity rule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_scale_amount_id", nullable = true)
    private PointScaleAmountEntity pointScaleAmount;

    public RulePointScaleAmountEntity() {
        //Here for JPA
    }

    public RulePointScaleAmountEntity(RuleEntity rule, PointScaleAmountEntity pointScaleAmount) {
        this.rule = rule;
        this.pointScaleAmount = pointScaleAmount;
    }

    public RuleEntity getRule() {
        return rule;
    }

    public void setRule(RuleEntity rule) {
        this.rule = rule;
    }

    public PointScaleAmountEntity getPointScaleAmount() {
        return pointScaleAmount;
    }

    public void setPointScaleAmount(PointScaleAmountEntity pointScaleAmount) {
        this.pointScaleAmount = pointScaleAmount;
    }
}
