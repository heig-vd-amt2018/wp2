package ch.heigvd.amt.wp2.model.entities;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "point_reward")
public class PointRewardEntity extends AbstractDomainModelEntity<Long> {

    @Column(name = "created_date", updatable = false, nullable = false)
    private Timestamp createdDate;

    @Column(name = "pointScale", nullable = false)
    private PointScaleEntity pointScale;

    @Column(name = "amount", nullable = false)
    private long amount;

    public PointRewardEntity() {
        //Here for JPA
    }

    public PointRewardEntity(PointScaleEntity pointScale, long amount) {
        this.createdDate = new Timestamp((new Date()).getTime());
        this.pointScale = pointScale;
        this.amount = amount;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public PointScaleEntity getPointScale() {
        return pointScale;
    }

    public void setPointScale(PointScaleEntity pointScale) {
        this.pointScale = pointScale;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
