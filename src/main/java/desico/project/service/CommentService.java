package desico.project.service;
import desico.project.model.entity.CommentEntity;
import desico.project.model.service.CommentServiceModel;
import desico.project.model.view.CommentViewModel;
import desico.project.model.view.VideoViewModel;

import java.util.List;

public interface CommentService {
    void createComment(CommentServiceModel commentServiceModel);
    List<CommentViewModel> findAllComments();
    CommentViewModel findById(String id);
    void deleteComment(String id);
//    CommentEntity findCommentById(String id);
}
