package desico.project.web;

import desico.project.model.binding.ChapterNameAddBindingModel;
import desico.project.model.service.ChapterNameServiceModel;
import desico.project.service.ChapterNameService;
import desico.project.service.UnitNameService;
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
@RequestMapping("/chapter")
public class ChapterNameController {
    private final ModelMapper modelMapper;
    private final ChapterNameService chapterNameService;
    private final UnitNameService unitNameService;

    public ChapterNameController(ModelMapper modelMapper, ChapterNameService chapterNameService, UnitNameService unitNameService) {
        this.modelMapper = modelMapper;
        this.chapterNameService = chapterNameService;
        this.unitNameService = unitNameService;
    }

    @GetMapping("/view")
    public String view(){
        return "chapter-view";
    }

    @GetMapping("/add")
    public String add(Model model) {
        if(!model.containsAttribute("chapterNameAddBindingModel")){
            model.addAttribute("chapterNameAddBindingModel",new ChapterNameAddBindingModel());
        }
        model.addAttribute("unitNames",unitNameService.findAllUnitNames());
        return "chapter-add";
    }
    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("chapterNameAddBindingModel") ChapterNameAddBindingModel chapterNameAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("chapterNameAddBindingModel", chapterNameAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.chapterNameAddBindingModel",bindingResult);
            return "redirect:add";
        }
        this.chapterNameService.add(this.modelMapper.map(chapterNameAddBindingModel, ChapterNameServiceModel.class));
        return  "redirect:/";
    }
}
