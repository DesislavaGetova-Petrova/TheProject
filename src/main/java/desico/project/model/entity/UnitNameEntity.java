package desico.project.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="units")
public class UnitNameEntity extends BaseEntity {
    private String unitName;

    public UnitNameEntity() {
    }

    @Column(name="unit_name", nullable = false,unique = true)
    public String getUnitName() {
        return unitName;
    }

    public UnitNameEntity setUnitName(String unitName) {
        this.unitName = unitName;
        return this;
    }

}
