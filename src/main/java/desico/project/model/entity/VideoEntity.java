package desico.project.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="videos")
public class VideoEntity extends BaseEntity{
    private ChapterNameEntity chapterName;
    private String videoName;
    private String videoUrl;
    private Float stars;

    public VideoEntity() {

    }
    @ManyToOne
    public ChapterNameEntity getChapterName() {
        return chapterName;
    }

    public VideoEntity setChapterName(ChapterNameEntity resource) {
        this.chapterName = resource;
        return this;
    }
    @Column(name="video_name",nullable = false,unique = true)
    public String getVideoName() {
        return videoName;
    }

    public VideoEntity setVideoName(String videoName) {
        this.videoName = videoName;
        return this;
    }

    @Column(nullable = false,unique = true, name="video_url")
    public String getVideoUrl() {
        return videoUrl;
    }

    public VideoEntity setVideoUrl(String video) {
        this.videoUrl = video;
        return this;
    }
    @Column
    public Float getStars() {
        return stars;
    }

    public VideoEntity setStars(Float stars) {
        this.stars = stars;
        return this;
    }
}
