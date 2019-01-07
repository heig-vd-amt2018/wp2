package ch.heigvd.amt.wp2.model.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "badge_reward")
public class BadgeRewardEntity extends AbstractDomainModelEntity<Long> {

    @Column(name = "created_date", updatable = false, nullable = false)
    private Timestamp createdDate;

    @Column(name = "badge", nullable = false)
    private BadgeEntity badge;

    public BadgeRewardEntity() {
        //Here for JPA
    }

    public BadgeRewardEntity(BadgeEntity badge) {
        this.createdDate = new Timestamp((new Date()).getTime());
        this.badge = badge;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public BadgeEntity getBadge() {
        return badge;
    }

    public void setBadge(BadgeEntity badge) {
        this.badge = badge;
    }
}
