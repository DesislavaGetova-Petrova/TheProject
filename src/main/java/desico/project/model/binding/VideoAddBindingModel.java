package desico.project.model.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class VideoAddBindingModel {
    private String chapterName;
    private String videoName;
    private String videoUrl;

    public VideoAddBindingModel() {
    }
    @NotEmpty
    public String getChapterName() {
        return chapterName;
    }

    public VideoAddBindingModel setChapterName(String chapterName) {
        this.chapterName = chapterName;
        return this;
    }
    @NotEmpty(message = "Полето не може да е празно!")
    @Size(min = 11, max = 20,message = "Наименованието трябва да е между 11 и 20 символа!")
    public String getVideoName() {
        return videoName;
    }

    public VideoAddBindingModel setVideoName(String videoName) {
        this.videoName = videoName;
        return this;
    }
    @NotEmpty(message = "Полето не може да е празно!")
    @Size(min = 3,message = "Наименованието трябва да съдържа повече от три символа!")
    public String getVideoUrl() {
        return videoUrl;
    }

    public VideoAddBindingModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }
}
