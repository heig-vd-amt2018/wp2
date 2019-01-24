package ch.heigvd.amt.wp2.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "point_scale")
public class PointScaleEntity extends AbstractDomainModelEntity<Long> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = true)
    private ApplicationEntity application;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    public PointScaleEntity() {
        //here fo JPA
    }

    public PointScaleEntity(ApplicationEntity application, String name, String description) {
        this.application = application;
        this.name = name;
        this.description = description;
    }

    public ApplicationEntity getApplication() {
        return application;
    }

    public void setApplication(ApplicationEntity application) {
        this.application = application;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
