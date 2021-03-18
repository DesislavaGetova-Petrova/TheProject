package desico.project.service.impl;

import desico.project.model.entity.ChapterNameEntity;
import desico.project.model.entity.VideoEntity;
import desico.project.model.service.VideoServiceModel;
import desico.project.model.service.VideoServiceModelCloud;
import desico.project.repository.VideoRepository;
import desico.project.service.ChapterNameService;
import desico.project.service.CloudinaryService;
import desico.project.service.VideoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
    private final ModelMapper modelMapper;
    private final ChapterNameService chapterNameService;
    private final CloudinaryService cloudinaryService;

    public VideoServiceImpl(VideoRepository videoRepository,
                            ModelMapper modelMapper,
                            ChapterNameService chapterNameService,
                            CloudinaryService cloudinaryService) {
        this.videoRepository = videoRepository;
        this.modelMapper = modelMapper;
        this.chapterNameService = chapterNameService;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void add(VideoServiceModel videoServiceModel) {
        VideoEntity videoEntity=this.modelMapper.map(videoServiceModel,VideoEntity.class);
        videoEntity.setChapterName(this.chapterNameService.findByChapterName(videoServiceModel.getChapterName()));
        this.videoRepository.saveAndFlush(videoEntity);

    }


    @Override
    public void addVideo(VideoServiceModelCloud videoServiceModel) throws IOException {
        MultipartFile videoUrl=videoServiceModel.getVideoUrl();
        String videoUrlNew =cloudinaryService.uploadVideo(videoUrl);


        VideoEntity videoEntity=new VideoEntity()
                .setVideoName(videoServiceModel.getVideoName())
                .setChapterName(this.chapterNameService.findByChapterName(videoServiceModel.getChapterName()))
                .setVideoUrl(videoUrlNew);
        videoRepository.saveAndFlush(videoEntity);

    }

    @Override
    public List<VideoEntity> findbyChapterName(ChapterNameEntity chapterNameEntity) {
        return videoRepository.findByChapterName(chapterNameEntity);
    }

    @Override
    public VideoEntity findByVideoName(String videoName) {
        return videoRepository.findByVideoName(videoName);
    }

    @Override
    public void addRating(VideoServiceModel videoServiceModel) {
        VideoEntity videoEntity=this.modelMapper.map(videoServiceModel,VideoEntity.class);


        this.videoRepository.saveAndFlush(videoEntity);

    }

}
