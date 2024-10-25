package com.microservice.auth.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

/**
 * esta entidad contiene la primera version para almacenar usuario y roles
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_name"})})
public class UserEntity2 implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "fist_name")
    private String fistName;

    private String country;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roleEnum.name()));
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
        return true;
    }
}
