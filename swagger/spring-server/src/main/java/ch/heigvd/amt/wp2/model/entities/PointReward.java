package ch.heigvd.amt.wp2.model.entities;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "point_reward")
public class PointReward extends AbstractDomainModelEntity<Long> {

    @Column(name = "created_date", updatable = false, nullable = false)
    private Timestamp createdDate;

    @Column(name = "pointScale", nullable = false)
    private PointScale pointScale;

    @Column(name = "amount", nullable = false)
    private long amount;

    public PointReward() {
        //Here for JPA
    }

    public PointReward(PointScale pointScale, long amount) {
        this.createdDate = new Timestamp((new Date()).getTime());
        this.pointScale = pointScale;
        this.amount = amount;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public PointScale getPointScale() {
        return pointScale;
    }

    public void setPointScale(PointScale pointScale) {
        this.pointScale = pointScale;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
