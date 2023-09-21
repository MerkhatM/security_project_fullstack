package com.example.Security_project.services;



import com.example.Security_project.models.Role;
import com.example.Security_project.models.User;
import com.example.Security_project.repositories.RoleRepository;
import com.example.Security_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;


public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user =userRepository.findByEmail(email );
        if(user==null){
            throw new UsernameNotFoundException("USER NOT FOUND!");
        }
        return user;
    }

    public String addUser(User newUser,String rePassword){
        User checkUser =userRepository.findByEmail(newUser.getEmail());
        if(checkUser!=null)
            return "sign-up?emailError";
        if(!newUser.getPassword().equals(rePassword))
            return "sign-up?passwordError";
        newUser.setPassword(passwordEncoder.encode(rePassword));
        Role role=roleRepository.findRoleUser();
        newUser.setRoles(List.of(role));
        userRepository.save(newUser);
        return "sign-in?success";

    }
}