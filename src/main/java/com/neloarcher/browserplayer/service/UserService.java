package com.neloarcher.browserplayer.service;

import com.neloarcher.browserplayer.domain.Role;
import com.neloarcher.browserplayer.domain.User;
import com.neloarcher.browserplayer.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(s);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        return user;
    }

    public boolean addUser(User user) {
        User userDb = userRepo.findByUsername(user.getUsername());
        if (userDb != null) {
            return false;
        }
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

        return true;
    }

    public List<User> findAll() {return userRepo.findAll();}
}