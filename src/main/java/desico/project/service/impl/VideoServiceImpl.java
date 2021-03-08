package desico.project.service.impl;

import desico.project.model.entity.VideoEntity;
import desico.project.model.service.VideoServiceModel;
import desico.project.repository.VideoRepository;
import desico.project.service.ChapterNameService;
import desico.project.service.VideoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
    private final ModelMapper modelMapper;
    private final ChapterNameService chapterNameService;

    public VideoServiceImpl(VideoRepository videoRepository, ModelMapper modelMapper, ChapterNameService chapterNameService) {
        this.videoRepository = videoRepository;
        this.modelMapper = modelMapper;
        this.chapterNameService = chapterNameService;
    }

    @Override
    public void add(VideoServiceModel videoServiceModel) {
        VideoEntity videoEntity=this.modelMapper.map(videoServiceModel,VideoEntity.class);
        videoEntity.setChapterName(this.chapterNameService.findByChapterName(videoServiceModel.getChapterName()));
        this.videoRepository.saveAndFlush(videoEntity);

    }

}
