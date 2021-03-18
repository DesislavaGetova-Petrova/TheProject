package desico.project.web;

import desico.project.model.binding.VideoAddBindingModel;
import desico.project.model.service.VideoServiceModel;
import desico.project.model.service.VideoServiceModelCloud;
import desico.project.service.ChapterNameService;
import desico.project.service.VideoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

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


    @GetMapping("/addLimited")
    public String addLimited(Model model){
        if(!model.containsAttribute("videoServiceModel")){
            model.addAttribute("videoServiceModel",new VideoServiceModelCloud());
        }

        model.addAttribute("chapterNames",chapterNameService.findAllChapterNames());
        return "video-add-limited";
    }
    @PostMapping("/addLimited")
    public String addConfirm(Model model, @ModelAttribute("videoServiceModel") VideoServiceModelCloud videoServiceModel

                            ) throws IOException {

//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("videoServiceModel", videoServiceModel);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.videoServiceModel",bindingResult);
//            return "redirect:add";
//        }
        this.videoService.addVideo(videoServiceModel);

        return  "redirect:/";
    }
    @GetMapping("/viewAll/{chapterName}")
    public String viewAll(@PathVariable String chapterName, Model model) {
        model.addAttribute("chapterName", chapterName);
        model.addAttribute("videoEntity",videoService.findbyChapterName(chapterNameService.findByChapterName(chapterName)));
        return "all-video-view";
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



    @GetMapping("/view/{videoName}")
    public String view(@PathVariable String videoName,Model model){
        model.addAttribute("video", this.videoService.findByVideoName(videoName));
        return  "video-view";
    }

    @PostMapping("/addRating/{videoName}")
    public String save(@PathVariable String videoName,Model model) {
        model.addAttribute("video", this.videoService.findByVideoName(videoName));

        return "redirect:/unit/view";
    }


}
