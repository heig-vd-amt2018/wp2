package ch.heigvd.amt.wp2.model.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Map;

@Entity
@Table(name = "event")
public class EventEntity extends AbstractDomainModelEntity<Long> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private PlayerEntity player;

    @Column(name = "created_date", updatable = false, nullable = false)
    private Timestamp createdDate;

    @Column(name = "event_type", updatable = false, nullable = true)
    private String eventType;

    @Column(name = "properties", updatable = false, nullable = true)
    private Map<String, String> properties;

    public EventEntity() {
        //here for JPA
    }

    public EventEntity(PlayerEntity player, Timestamp createdDate, String eventType, Map<String, String> properties) {
        this.player = player;
        this.createdDate = createdDate;
        this.eventType = eventType;
        this.properties = properties;
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

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}