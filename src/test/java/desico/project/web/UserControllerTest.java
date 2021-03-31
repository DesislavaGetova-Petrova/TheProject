package desico.project.web;

import desico.project.model.entity.UserEntity;
import desico.project.model.entity.UserRoleEntity;
import desico.project.model.enums.UserRole;
import desico.project.repository.UserRepository;
import desico.project.repository.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @BeforeEach
    public void init() throws IOException {
        UserEntity userEntity = new UserEntity();

        if (userRoleRepository.count() == 0) {
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setRole(UserRole.USER);
            userRoleRepository.save(userRoleEntity);
            userEntity.setUsername("pesho").setEmail("p@p").setPassword("password").setRoles(List.of(userRoleEntity));
            userRepository.save(userEntity);
        }
        if (userRepository.count() == 0){
             UserRoleEntity role = userRoleRepository.findByRole(UserRole.USER).get();
            userEntity.setUsername("pesho").setEmail("p@p").setPassword("password").setRoles(List.of(role));
            userRepository.save(userEntity);
        }
    }

    @Test
    void registerShouldReturnValidStatusAndView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                 "/users/register"
        )).
                andExpect(status().isOk()).
                andExpect(view().name("register"));
    }
}
