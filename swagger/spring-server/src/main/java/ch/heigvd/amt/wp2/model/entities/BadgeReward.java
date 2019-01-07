package ch.heigvd.amt.wp2.model.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "badge_reward")
public class BadgeReward extends AbstractDomainModelEntity<Long> {

    @Column(name = "created_date", updatable = false, nullable = false)
    private Timestamp createdDate;

    @Column(name = "badge", nullable = false)
    private Badge badge;

    public BadgeReward() {
        //Here for JPA
    }

    public BadgeReward(Badge badge) {
        this.createdDate = new Timestamp((new Date()).getTime());
        this.badge = badge;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }
}
