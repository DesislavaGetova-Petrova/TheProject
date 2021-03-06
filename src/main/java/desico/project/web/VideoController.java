package desico.project.web;

import desico.project.model.entity.RatingEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/video")
public class VideoController {
//    private final ResourceService resourseService;
//    private final ModelMapper modelMapper;
//
//    public ResourceController(ResourceService resourseService, ModelMapper modelMapper) {
//        this.resourseService = resourseService;
//        this.modelMapper = modelMapper;
//}
    @GetMapping("/add")
    public String add(){
        return "video-add";
    }
    @GetMapping("/view")
    public String view(){
        return "video-view";
    }
    @GetMapping("/addRating")
    public String addRating(Model model){
        model.addAttribute("rating", new RatingEntity());
        return "video-add-rating";
    }
    @PostMapping("/addRating")
    public String save(RatingEntity rating, Model model) {
        model.addAttribute("rating", rating);
        return "video-view";
    }


}
