package desico.project.service;

import desico.project.model.entity.VideoEntity;

import java.util.List;

public interface ResourceService {
    List<String> findAllNames();

    VideoEntity findByVideoName(String name);
}
