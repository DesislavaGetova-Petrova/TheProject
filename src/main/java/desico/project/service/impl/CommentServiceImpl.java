package desico.project.service.impl;

import desico.project.model.entity.CommentEntity;
import desico.project.model.entity.UserEntity;
import desico.project.model.entity.VideoEntity;
import desico.project.model.service.CommentServiceModel;
import desico.project.model.view.CommentViewModel;
import desico.project.repository.CommentRepository;
import desico.project.service.CommentService;
import desico.project.service.UserService;
import desico.project.service.VideoService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final VideoService videoService;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, VideoService videoService, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.videoService = videoService;
        this.modelMapper = modelMapper;
    }


    @Override
    public void createComment(CommentServiceModel commentServiceModel) {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username = authentication.getName();
        UserEntity userEntity = userService.findByName(username);
        VideoEntity videoEntity=videoService.findByVideoName(commentServiceModel.getVideoEntity());


        CommentEntity commentEntity = new CommentEntity()

                .setUserEntity(userEntity)
                .setVideoEntity(videoEntity)
                .setDateTime(LocalDateTime.now())
                .setTextContent(commentServiceModel.getTextContent());;

        commentRepository.save(commentEntity);

    }

    @Override
    public List<CommentViewModel> findAllComments() {
        List<CommentViewModel> collect = commentRepository
                .findAll()
                .stream()
                .map(commentEntity -> {
                    CommentViewModel commentViewModel = new CommentViewModel();
                    commentViewModel.setId(commentEntity.getId());
                    commentViewModel.setAuthor(commentEntity.getUserEntity().getUsername());
                    commentViewModel.setVideo(commentEntity.getVideoEntity().getVideoName());
                    commentViewModel.setDateTime(commentEntity.getDateTime());
                    commentViewModel.setTextContent(commentEntity.getTextContent());
                    return commentViewModel;
                })
                .collect(Collectors.toList());

        Collections.sort(collect, new Comparator<CommentViewModel>() {
            public int compare(CommentViewModel o1, CommentViewModel o2) {
                return o1.getDateTime().compareTo(o2.getDateTime());
            }
        });
        return collect;
    }

    @Override
    public CommentViewModel findById(String id) {
        return commentRepository
                .findById(id)
                .map(commentEntity ->  {
                    CommentViewModel commentViewModel = new CommentViewModel();
                    commentViewModel.setId(commentEntity.getId());
                    commentViewModel.setAuthor(commentEntity.getUserEntity().getUsername());
                    commentViewModel.setVideo(commentEntity.getVideoEntity().getVideoName());
                    commentViewModel.setDateTime(commentEntity.getDateTime());
                    commentViewModel.setTextContent(commentEntity.getTextContent());
                    return commentViewModel;
                })
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void deleteComment(String id) {
        CommentEntity comment = this.commentRepository.getCommentById(id).get();
        String videoId = comment.getVideoEntity().getId();
        comment.setVideoEntity(null);
        String userId = comment.getUserEntity().getId();
        comment.setUserEntity(null);
        this.commentRepository.saveAndFlush(comment);
        this.commentRepository.deleteById(id);
    }

//    @Override
//    public CommentEntity findCommentById(String id) {
//        return commentRepository.findById(id).orElse(null);
//    }

}
