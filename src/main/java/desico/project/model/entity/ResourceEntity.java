package desico.project.model.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="resources")
public class ResourceEntity extends BaseEntity {
    private UnitNameEntity unitName;
    private String resourceName;
    private List<VideoEntity> videos;

    public ResourceEntity() {
    }
    @ManyToOne
    public UnitNameEntity getUnitName() {
        return unitName;
    }

    public ResourceEntity setUnitName(UnitNameEntity unitName) {
        this.unitName = unitName;
        return this;
    }
    @Column(name="resource_name",nullable = false,unique = true)
    public String getResourceName() {
        return resourceName;
    }

    public ResourceEntity setResourceName(String resourceName) {
        this.resourceName = resourceName;
        return this;
    }

    @OneToMany
    public List<VideoEntity> getVideos() {
        return videos;
    }

    public ResourceEntity setVideos(List<VideoEntity> videos) {
        this.videos = videos;
        return this;
    }
}
