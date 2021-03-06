package com.borman.geneabook.entity;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Entity
public class User implements UserDetails, Serializable, CredentialsContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 32)
    private String nickname;

    @Email
    @NotBlank
    @Size(max = 128)
    @Column(unique = true)
    private String email;

    private int enabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roleSet;

    @NotBlank
    @Size(min = 8, max = 128)
    private String password;

    @Transient
    private String confirmPassword;

    private LocalDateTime dateRegisterLogin;

    private LocalDateTime dateUpdateLogin;

    @OneToOne(fetch = FetchType.EAGER,
            cascade =  CascadeType.REMOVE,
            mappedBy = "user")
    private UserProfile userProfile;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleSet;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled == 1;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public LocalDateTime getDateRegisterLogin() {
        return dateRegisterLogin;
    }

    @PrePersist
    public void setDateRegisterLogin() {
        this.dateRegisterLogin = LocalDateTime.now();
    }

    public LocalDateTime getDateUpdateLogin() {
        return dateUpdateLogin;
    }

    @PreUpdate
    public void setDateUpdateLogin() {
        this.dateUpdateLogin = LocalDateTime.now();
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }


    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public void setDateRegisterLogin(LocalDateTime dateRegisterLogin) {
        this.dateRegisterLogin = dateRegisterLogin;
    }

    public void setDateUpdateLogin(LocalDateTime dateUpdateLogin) {
        this.dateUpdateLogin = dateUpdateLogin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", roleSet=" + roleSet +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", dateRegisterLogin=" + dateRegisterLogin +
                ", dateUpdateLogin=" + dateUpdateLogin +
                ", userProfile=" + userProfile +
                '}';
    }

    @Override
    public void eraseCredentials() {
//        super.eraseCredentials();
//        if (this.userAuthentication != null && CredentialsContainer.class.isAssignableFrom(this.userAuthentication.getClass())) {
//            CredentialsContainer.class.cast(this.userAuthentication).eraseCredentials();
//        }
    }
}
