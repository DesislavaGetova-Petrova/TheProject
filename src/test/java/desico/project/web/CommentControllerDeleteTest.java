package desico.project.web;

import desico.project.model.entity.*;
import desico.project.model.enums.UserRole;
import desico.project.repository.*;
import desico.project.service.CloudinaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerDeleteTest {
    private String  testCommentId;

    @Autowired
    private MockMvc mockMvc;
    //    @Autowired
//    private LogRepository logRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UnitNameRepository unitNameRepository;
    @Autowired
    private ChapterNameRepository chapterNameRepository;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserRepository userRepository;

    @MockBean
    CloudinaryService mockCloudinaryService;
    @BeforeEach
    public void init() throws IOException {
        UnitNameEntity unitNameEntity = new UnitNameEntity();
        unitNameEntity.setUnitName("unit");
        unitNameEntity = unitNameRepository.save(unitNameEntity);

        ChapterNameEntity chapterNameEntity = new ChapterNameEntity();
        chapterNameEntity.setChapterName("Chapter");
        chapterNameEntity.setUnitName(unitNameEntity);
        chapterNameEntity = chapterNameRepository.save(chapterNameEntity);

        VideoEntity video= new VideoEntity();
        video.setChapterName(chapterNameEntity);
        video.setVideoName("video");
        video.setVideoUrl("videoUrl");
        video=videoRepository.save(video);

        UserRole userRole=UserRole.USER;
        UserRoleEntity userRoleEntity=new UserRoleEntity();
        userRoleEntity.setRole(userRole);
        userRoleEntity=userRoleRepository.save(userRoleEntity);

        UserEntity user=new UserEntity();
        user.setPassword("123456").setEmail("d@d").setUsername("pesho").setRoles(List.of(userRoleEntity));
        user=userRepository.save(user);

        CommentEntity commentEntity=new CommentEntity();
        commentEntity.setDateTime(LocalDateTime.now());
        commentEntity.setTextContent("textttttttttttttt");
        commentEntity.setVideoEntity(video);
        commentEntity.setUserEntity(user);
        commentEntity=commentRepository.save(commentEntity);

        testCommentId=commentEntity.getId();


    }
//
//    @AfterEach
//    public void clear(){
//
//        videoRepository.deleteAll();
//        commentRepository.deleteAll();
//        chapterNameRepository.deleteAll();
//        unitNameRepository.deleteAll();
////
////        userRepository.deleteAll();
////
//    }
//
//    @Test
//    @WithMockUser(value = "pesho", roles = {"MODERATOR","ADMIN"})
//    void testComments() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get( "/comments")).
//                andExpect(status().isOk()).
//                andExpect(view().name("comments"));
//
//    }
    @Test
    @WithMockUser(value = "pesho", roles = {"MODERATOR","ADMIN"})
    void testDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                 "/comments/delete/{testCommentId}", testCommentId)).
                andExpect(status().is3xxRedirection()).
                andExpect(view().name("redirect:/comments"));

    }
}
