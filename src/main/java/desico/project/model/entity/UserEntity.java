package desico.project.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class UserEntity extends BaseEntity {
    private String username;
    private String password;
    private String email;
    private List<UserRoleEntity> roles = new ArrayList<>();
    private Set<CommentEntity> comments=new HashSet<>();

    public UserEntity() {
    }
    @Column(nullable = false)
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }
    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }
    @ManyToMany(fetch = FetchType.EAGER)
    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }
    @Column
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }
    public UserEntity addRole(UserRoleEntity roleEntity) {
        this.roles.add(roleEntity);
        return this;
    }
    @OneToMany(mappedBy = "userEntity",targetEntity = CommentEntity.class, fetch = FetchType.EAGER,cascade= {CascadeType.ALL})

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public UserEntity setComments(Set<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }
}
