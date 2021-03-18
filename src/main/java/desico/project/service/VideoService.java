package desico.project.service;

import desico.project.model.entity.ChapterNameEntity;
import desico.project.model.entity.VideoEntity;
import desico.project.model.service.VideoServiceModel;
import desico.project.model.service.VideoServiceModelCloud;

import java.io.IOException;
import java.util.List;

public interface VideoService {
    void add(VideoServiceModel videoServiceModel);

    void addVideo(VideoServiceModelCloud videoServiceModel) throws IOException;

    List<VideoEntity> findbyChapterName(ChapterNameEntity chapterNameEntity);

    VideoEntity findByVideoName(String videoName);

     void addRating(VideoServiceModel videoServiceModel);

}
