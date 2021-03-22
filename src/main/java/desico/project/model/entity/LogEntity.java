package desico.project.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
public class LogEntity extends BaseEntity{


    private UserEntity userEntity;
    private VideoEntity videoEntity;
    private String action;
    private LocalDateTime dateTime;

    public LogEntity() {
    }
    @ManyToOne
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public LogEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }
    @ManyToOne
    public VideoEntity getVideoEntity() {
        return videoEntity;
    }

    public LogEntity setVideoEntity(VideoEntity videoEntity) {
        this.videoEntity = videoEntity;
        return this;
    }
    @Column(name = "action", nullable = false)
    public String getAction() {
        return action;
    }

    public LogEntity setAction(String action) {
        this.action = action;
        return this;
    }
    @Column(name = "date_time", nullable = false)
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LogEntity setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }
}