package com.ncs503.Babybook.auth.security;

import com.ncs503.Babybook.models.entity.UserEntity;
import com.ncs503.Babybook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userDB = userRepository.findByUsername(username);
        if (((Optional<?>) userDB).isEmpty()){
            throw new UsernameNotFoundException(username);
        }
        UserEntity user = userDB.get();
        return new User(user.getUsername(), user.getPassword(), mapRoles(user.getRoleId));
    }

    private Collection<? extends GrantedAuthority> mapRoles (Set<RoleEntity> roles){
        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());
    }
}
