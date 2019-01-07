package ch.heigvd.amt.wp2.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "rule")
public class Rule extends AbstractDomainModelEntity<Long>{

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "eventName", nullable = false)
    private String eventName;

    @Column(name = "pointScale", nullable = false)
    private PointScale pointScale;

    @Column(name = "badge", nullable = false)
    private Badge badge;

    @Column(name = "threshold", nullable = false)
    private Long threshold;

    public Rule() {
        //here fo JPA
    }

    public Rule(String name, String eventName, PointScale pointScale, Badge badge, Long threshold) {
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

    public PointScale getPointScale() {
        return pointScale;
    }

    public void setPointScale(PointScale pointScale) {
        this.pointScale = pointScale;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public Long getThreshold() {
        return threshold;
    }

    public void setThreshold(Long threshold) {
        this.threshold = threshold;
    }
}
