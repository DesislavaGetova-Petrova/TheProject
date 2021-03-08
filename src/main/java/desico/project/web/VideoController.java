package desico.project.web;

import desico.project.model.binding.ChapterNameAddBindingModel;
import desico.project.model.binding.VideoAddBindingModel;
import desico.project.model.entity.RatingEntity;
import desico.project.model.entity.VideoEntity;
import desico.project.model.service.ChapterNameServiceModel;
import desico.project.model.service.VideoServiceModel;
import desico.project.service.ChapterNameService;
import desico.project.service.VideoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/video")
public class VideoController {
private final VideoService videoService;
private final ModelMapper modelMapper;
private final ChapterNameService chapterNameService;

    public VideoController(VideoService videoService, ModelMapper modelMapper, ChapterNameService chapterNameService) {
        this.videoService = videoService;
        this.modelMapper = modelMapper;
        this.chapterNameService = chapterNameService;
    }


    @GetMapping("/add")
    public String add(Model model){
        if(!model.containsAttribute("videoAddBindingModel")){
            model.addAttribute("videoAddBindingModel",new VideoAddBindingModel());
        }
        model.addAttribute("chapterNames",chapterNameService.findAllChapterNames());
        return "video-add";
    }
    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("videoAddBindingModel") VideoAddBindingModel videoAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("videoAddBindingModel", videoAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.videoAddBindingModel",bindingResult);
            return "redirect:add";
        }
        this.videoService.add(this.modelMapper.map(videoAddBindingModel, VideoServiceModel.class));
        return  "redirect:/";
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
