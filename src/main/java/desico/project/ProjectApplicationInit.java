package desico.project;

import desico.project.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProjectApplicationInit implements CommandLineRunner {
    private  final UserService userService;

    public ProjectApplicationInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.seedUsers();
    }
}
