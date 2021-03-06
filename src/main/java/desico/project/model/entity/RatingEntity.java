package desico.project.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ratings")
public class RatingEntity extends BaseEntity {
    private float stars;

    public RatingEntity() {
    }

    public float getStars() {
        return stars;
    }

    public RatingEntity setStars(float stars) {
        this.stars = stars;
        return this;
    }
}
