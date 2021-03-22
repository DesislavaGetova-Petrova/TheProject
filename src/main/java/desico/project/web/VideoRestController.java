package desico.project.web;
import java.util.List;

import desico.project.model.entity.VideoEntity;
import desico.project.repository.VideoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/video/viewAll")
@RestController
public class VideoRestController {
    private final VideoRepository videoRepository;
    private final ModelMapper modelMapper;

    public VideoRestController(VideoRepository videoRepository, ModelMapper modelMapper) {
        this.videoRepository = videoRepository;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/api")
    public ResponseEntity<List<VideoEntity>> findAll() {
        return ResponseEntity
                .ok()
                .body(videoRepository.findAll());
    }
}
