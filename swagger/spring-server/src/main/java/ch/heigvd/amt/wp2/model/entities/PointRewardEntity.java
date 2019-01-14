package ch.heigvd.amt.wp2.model.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "point_reward")
public class PointRewardEntity extends AbstractDomainModelEntity<Long> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private PlayerEntity player;

    @Column(name = "created_date", updatable = false, nullable = false)
    private Timestamp createdDate;

    @Column(name = "point_scale", nullable = false)
    private PointScaleEntity pointScale;

    @Column(name = "amount", nullable = false)
    private long amount;

    public PointRewardEntity() {
        //Here for JPA
    }

    public PointRewardEntity(PlayerEntity player, PointScaleEntity pointScale, long amount) {
        this.player = player;
        this.createdDate = new Timestamp((new Date()).getTime());
        this.pointScale = pointScale;
        this.amount = amount;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
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
