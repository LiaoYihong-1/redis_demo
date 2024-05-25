package com.example.videoBack.service;

import com.example.videoBack.dao.model.Role;
import com.example.videoBack.dao.model.User;
import com.example.videoBack.dao.model.Video;
import com.example.videoBack.dao.model.enums.UserType;
import com.example.videoBack.dao.repository.RoleRepo;
import com.example.videoBack.dao.repository.UserRepo;
import com.example.videoBack.dao.repository.VideoRepo;
import com.example.videoBack.dao.request.AccountRequest;
import com.example.videoBack.domain.MyUserDetails;
import com.example.videoBack.dto.SecurityAccount;
import com.example.videoBack.exception.InvalidParameterException;
import com.example.videoBack.exception.ResourceNotFoundException;
import com.example.videoBack.utils.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class AccountService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private StringRedisTemplate template;
    private final static ObjectMapper mapper = new ObjectMapper();
    public ResponseEntity<?> register(AccountRequest account) throws InvalidParameterException, ResourceNotFoundException{
        Optional<?> optional = userRepo.findUserByEmail(account.getEmail());
        if(optional.isPresent()){
            throw new InvalidParameterException("This email is in use");
        }
        User u = new User();
        u.setEmail(account.getEmail());
        u.setEncryptedPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
        u.setName(account.getName());
        Optional<Role> roleOptional = roleRepo.findRoleByName("USER");
        if(roleOptional.isEmpty()){
            throw new ResourceNotFoundException("No role USER");
        }
        u.setRole(roleOptional.get());
        userRepo.save(u);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> login(String email, String password) throws ResourceNotFoundException, JsonProcessingException {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email,password);
        Authentication authentication = authenticationManager.authenticate(token);
        if(Objects.isNull(authentication)){
            throw new ResourceNotFoundException("Email or password not right");
        }
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        SecurityAccount securityAccount = myUserDetails.getUser();
        String userId = securityAccount.getId().toString();
        /**
         * create token
         */
        Optional<User> userOptional = userRepo.findById(securityAccount.getId());
        if(userOptional.isEmpty()){
            throw new ResourceNotFoundException("Id wrong");
        }
        User user = userOptional.get();
        String role = user.getRole().getName();
        String row = role+":"+userId;
        String jwtToken = JwtUtils.createToken(row);
        template.opsForHash().put(row,"email", securityAccount.getEmail());
        template.opsForHash().put(row,"permissions",mapper.writeValueAsString(securityAccount.getPermissions()));
        template.opsForHash().put(row,"id",securityAccount.getId().toString());
        template.expire(row,30*60, TimeUnit.SECONDS);
        return ResponseEntity.ok(jwtToken);
    }
}
