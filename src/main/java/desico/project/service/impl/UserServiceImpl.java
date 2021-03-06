package desico.project.service.impl;

import desico.project.model.entity.UserEntity;
import desico.project.model.entity.UserRoleEntity;
import desico.project.model.enums.UserRole;
import desico.project.model.service.UserRegistrationServiceModel;
import desico.project.repository.UserRepository;
import desico.project.repository.UserRoleRepository;
import desico.project.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordEncoder encoder;
    private final ModelMapper modelMapper;
    private final ProjectUserService projectUserService;

    public UserServiceImpl(UserRoleRepository userRoleRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder, PasswordEncoder encoder, ModelMapper modelMapper, ProjectUserService projectUserService) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.encoder = encoder;
        this.modelMapper = modelMapper;
        this.projectUserService = projectUserService;
    }


    @Override
    public void seedUsers() {

        if (userRepository.count() == 0) {

            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRole.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRole.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));

            UserEntity admin = new UserEntity().setUsername("admin").setEmail("desy@abv.bg").setPassword(passwordEncoder.encode("123456"));
            UserEntity user = new UserEntity().setUsername("user").setEmail("pepi@abv.bg").setPassword(passwordEncoder.encode("123456"));
            admin.setRoles(List.of(adminRole, userRole));
            user.setRoles(List.of(userRole));

            userRepository.saveAll(List.of(admin, user));
        }
    }
    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel serviceModel) {
        UserEntity newUser = modelMapper.map(serviceModel, UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(serviceModel.getPassword()));

        UserRoleEntity userRole =  userRoleRepository.
                findByRole(UserRole.USER).orElseThrow(
                () -> new IllegalStateException("USER role not found. Please seed the roles."));

        newUser.addRole(userRole);

        newUser = userRepository.save(newUser);

        UserDetails principal = projectUserService.loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public boolean userNameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

}
