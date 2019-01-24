package ch.heigvd.amt.wp2.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "point_scale_amount")
public class PointScaleAmountEntity extends AbstractDomainModelEntity<Long> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_scale_id", nullable = false)
    private PointScaleDescriptionEntity pointScale;

    @Column(name = "amount", nullable = true)
    private int amount;

    public PointScaleAmountEntity() {
        //here fo JPA
    }

    public PointScaleAmountEntity(PointScaleDescriptionEntity pointScale, int amount) {
        this.pointScale = pointScale;
        this.amount = amount;
    }

    public PointScaleDescriptionEntity getPointScale() {
        return pointScale;
    }

    public void setPointScale(PointScaleDescriptionEntity pointScale) {
        this.pointScale = pointScale;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
