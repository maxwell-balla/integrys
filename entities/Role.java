package com.integrys.backend.entities;

import com.integrys.backend.entities.enums.Authority;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;


@Data
@Entity
@NoArgsConstructor
@Table(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority{
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @Column(updatable = false, nullable = false)
    private String id;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Authority name;

//    @JsonManagedReference("role-users")
//    @ManyToMany(mappedBy = "authorities")
//    private Set<User> users = new HashSet<>();

    public Role(Authority authority) {
        this.name = authority;
    }


    @Override
    public String getAuthority() {
        return this.name.toString();
    }

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "role_permissions", joinColumns = {@JoinColumn(name = "role_id")},
//            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
//    private Set<Permission> permissions;

}
