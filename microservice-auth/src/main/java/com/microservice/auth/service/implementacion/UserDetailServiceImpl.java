package com.microservice.auth.service.implementacion;

import com.microservice.auth.exception.NotUsernameFoundException;
import com.microservice.auth.jpa.entity.UserEntity;
import com.microservice.auth.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final IUserRepository iUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = iUserRepository.findByUsername(username)
                .orElseThrow(() -> new NotUsernameFoundException("User "+ username +" no encontrado", HttpStatus.BAD_REQUEST));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userEntity.getRoles()
                .forEach(roleEntity ->
                        authorities.add(
                                new SimpleGrantedAuthority("ROLE_".concat(roleEntity.getRoleEnum().name()))));

        userEntity.getRoles().stream()
                .flatMap(roleEntity ->roleEntity.getPermissionList().stream())
                .forEach(permissionEntity -> authorities.add(new SimpleGrantedAuthority(permissionEntity.getName())));

        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnable(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(),
                authorities);
    }
}
