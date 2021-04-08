package desico.project.model.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {


   private String textContent;
   private UserEntity userEntity;
   private VideoEntity videoEntity;
   private LocalDateTime dateTime;

    public CommentEntity() {
    }
    @Column(name = "text_content", columnDefinition = "TEXT")
    public String getTextContent() {
        return textContent;
    }

    public CommentEntity setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }



    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public CommentEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    @ManyToOne( fetch = FetchType.EAGER,cascade= {CascadeType.MERGE})
    @JoinColumn(name = "video_id", referencedColumnName = "id")
    public VideoEntity getVideoEntity() {
        return videoEntity;
    }

    public CommentEntity setVideoEntity(VideoEntity videoEntity) {
        this.videoEntity = videoEntity;
        return this;
    }
    @Column(name = "date_time")
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public CommentEntity setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }
}

