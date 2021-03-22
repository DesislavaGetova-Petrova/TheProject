package desico.project.service.impl;

import desico.project.model.entity.LogEntity;
import desico.project.model.entity.UserEntity;
import desico.project.model.entity.VideoEntity;
import desico.project.model.service.LogServiceModel;
import desico.project.repository.LogRepository;
import desico.project.service.LogService;
import desico.project.service.UserService;
import desico.project.service.VideoService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final VideoService videoService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public LogServiceImpl(LogRepository logRepository, VideoService videoService, UserService userService, ModelMapper modelMapper) {
        this.logRepository = logRepository;
        this.videoService = videoService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @Override
    public void createLog(String action, String videoId) {
        VideoEntity videoEntity = videoService
                .findEntityById(videoId);

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username = authentication.getName();
        UserEntity userEntity = userService.findByName(username);

        LogEntity logEntity = new LogEntity()
                .setVideoEntity(videoEntity)
                .setUserEntity(userEntity)
                .setAction(action)
                .setDateTime(LocalDateTime.now());

        logRepository.save(logEntity);

    }

    @Override
    public List<LogServiceModel> findAllLogs() {
        return logRepository
                .findAll()
                .stream()
                .map(logEntity -> {
                    LogServiceModel logServiceModel = modelMapper
                            .map(logEntity, LogServiceModel.class);
                    logServiceModel.setVideoName(logEntity.getVideoEntity().getVideoName())
                            .setUser(logEntity.getUserEntity().getUsername());

                    return logServiceModel;
                })
                .collect(Collectors.toList());
    }
}

