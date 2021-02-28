package desico.project.service.impl;

import desico.project.model.entity.UserEntity;
import desico.project.model.entity.UserRoleEntity;
import desico.project.model.enums.UserRole;
import desico.project.repository.UserRepository;
import desico.project.repository.UserRoleRepository;
import desico.project.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRoleRepository userRoleRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void seedUsers() {

        if (userRepository.count() == 0) {

            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRole.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRole.USER);
            UserRoleEntity userVipRole = new UserRoleEntity().setRole(UserRole.USERVIP);

            userRoleRepository.saveAll(List.of(adminRole, userRole, userVipRole));

            UserEntity admin = new UserEntity().setName("admin").setPassword(passwordEncoder.encode("topsecret"));
            UserEntity user = new UserEntity().setName("user").setPassword(passwordEncoder.encode("topsecret"));
            UserEntity userVip = new UserEntity().setName("userVip").setPassword(passwordEncoder.encode("topsecret"));

            admin.setRoles(List.of(adminRole, userRole,userVipRole));
            userVip.setRoles(List.of(userRole,userVipRole));

            user.setRoles(List.of(userRole));

            userRepository.saveAll(List.of(admin, user, userVip));
        }
    }
}
