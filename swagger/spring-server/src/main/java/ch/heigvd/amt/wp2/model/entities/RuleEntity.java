package ch.heigvd.amt.wp2.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "rule")
public class RuleEntity extends AbstractDomainModelEntity<Long> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private ApplicationEntity application;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "event_name", nullable = false)
    private String eventName;

    @Column(name = "point_scale", nullable = false)
    private PointScaleEntity pointScale;

    @Column(name = "badge", nullable = false)
    private BadgeEntity badge;

    @Column(name = "threshold", nullable = false)
    private Long threshold;

    public RuleEntity() {
        //here fo JPA
    }

    public RuleEntity(ApplicationEntity application, String name, String eventName, PointScaleEntity pointScale, BadgeEntity badge, Long threshold) {
        this.application = application;
        this.name = name;
        this.eventName = eventName;
        this.pointScale = pointScale;
        this.badge = badge;
        this.threshold = threshold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public PointScaleEntity getPointScale() {
        return pointScale;
    }

    public void setPointScale(PointScaleEntity pointScale) {
        this.pointScale = pointScale;
    }

    public BadgeEntity getBadge() {
        return badge;
    }

    public void setBadge(BadgeEntity badge) {
        this.badge = badge;
    }

    public Long getThreshold() {
        return threshold;
    }

    public void setThreshold(Long threshold) {
        this.threshold = threshold;
    }
}
