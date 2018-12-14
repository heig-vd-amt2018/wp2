package ch.heigvd.amt.wp2.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "point_scale")
public class PointScale extends AbstractDomainModelEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    public PointScale() {
        //here fo JPA
    }

    public PointScale(String name, String description) {
        this.name = name;
        this.description = description;
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
