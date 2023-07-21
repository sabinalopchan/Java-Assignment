package com.example.java_application.services.impl;

import com.example.java_application.entity.User;
import com.example.java_application.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        try{

            User user = userRepo.findByEmail(email);

            if (user==null){
                throw new UsernameNotFoundException("No User Found");
            }else{
                return new CustomUserDetails(user);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
