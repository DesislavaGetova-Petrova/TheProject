package desico.project.web;

import desico.project.model.view.CommentViewModel;
import desico.project.model.view.VideoViewModel;
import desico.project.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping
    public String comments(Model model){
        model.addAttribute("comments",
                commentService.findAllComments());
        return "comments";
    }
    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable String id, Model model) {
//        CommentViewModel commentViewModel = commentService.findById(id);
        this.commentService.deleteComment(id);
        return  "redirect:/comments";
    }
}
