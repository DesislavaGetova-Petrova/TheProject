package desico.project.service;

import desico.project.model.service.UserRegistrationServiceModel;

public interface UserService {

    void seedUsers();

    void registerAndLoginUser(UserRegistrationServiceModel serviceModel);

    boolean userNameExists(String username);

}