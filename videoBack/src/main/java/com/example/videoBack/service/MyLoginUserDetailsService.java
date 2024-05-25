package com.example.videoBack.service;

import com.example.videoBack.dao.model.User;
import com.example.videoBack.dao.model.enums.PrivilegeType;
import com.example.videoBack.dao.repository.UserRepo;
import com.example.videoBack.domain.MyUserDetails;
import com.example.videoBack.dto.SecurityAccount;
import com.example.videoBack.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MyLoginUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    /**
     *
     * @param username email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = userRepo.findUserByEmail(username);
        if(optional.isEmpty()){
            throw new UsernameNotFoundException("No account with this email");
        }
        User user = optional.get();
        List<String> permissions = user.getRole().getPrivileges().stream().map(privilege -> privilege.getPrivilegeType().name())
                .collect(Collectors.toList());
        SecurityAccount securityAccount = new SecurityAccount();
        securityAccount.setEmail(user.getEmail());
        securityAccount.setPassword(user.getEncryptedPassword());
        securityAccount.setId(user.getId());
        securityAccount.setPermissions(permissions);
        return new MyUserDetails(securityAccount, permissions);
    }
}
