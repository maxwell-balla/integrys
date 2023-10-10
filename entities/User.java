package com.integrys.backend.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.integrys.backend.services.BCryptManagerUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails{

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @Column(updatable = false, nullable = false)
    private String id;

    @Column
    private String avatar;


    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 255, message = "Username must be between 3 and 255 characters long")
    private String username;

    @JsonIgnore
    @NotEmpty
    @Column(nullable = false)
    private String password;

    @NotEmpty
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    private String phoneNumber;

    @Column
    private String firstname;

    @NotEmpty
    @Column(nullable = false)
    private String lastname;

    private String address;


    //    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "users_roles", joinColumns = {@JoinColumn(name = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> authorities;

    @ApiModelProperty(hidden = true)
    private boolean accountNonExpired;

    @ApiModelProperty(hidden = true)
    private boolean accountNonLocked;

    @ApiModelProperty(hidden = true)
    private boolean credentialsNonExpired;

    @ApiModelProperty(hidden = true)
    private boolean enabled;


    public User() {
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public User(String username, String password, String email, String telephone, String firstname, String lastname, Role role) {
        this();
        this.username = username;
        this.password = BCryptManagerUtil.passwordencoder().encode(password);
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = telephone;
        this.authorities = Collections.singletonList(role);
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<Role> roles = this.getRoles();
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//        for (Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
//        }
//
//        return authorities;
//    }

//    @JsonDeserialize(using = CustomAuthorityDeserializer.class)
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        String roles;
//        if(this.authorities != null){
//            roles = StringUtils.collectionToCommaDelimitedString(this.authorities.stream()
//                    .map(Enum::name).collect(Collectors.toList()));
//
//        }else{
//            roles = "";
//        }
//        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        final List<SimpleGrantedAuthority> authorities = new LinkedList<>();
//        if (enabled) {
//            if (this.getUser().isAdmin()) {
//                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//            }
//            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        }
//        return authorities;
//    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (!password.isEmpty()) {
            this.password = BCryptManagerUtil.passwordencoder().encode(password);
        }
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
