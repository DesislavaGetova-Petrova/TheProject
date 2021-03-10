package desico.project.service;

import desico.project.model.service.ChapterNameServiceModel;
import desico.project.model.service.VideoServiceModel;

import java.util.List;

public interface VideoService {
    void add(VideoServiceModel videoServiceModel);
    List<String>findAllVideoNames();
}
