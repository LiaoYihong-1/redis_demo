package com.example.videoBack.utils;

import com.example.videoBack.dto.SecurityAccount;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class TokenFilter extends OncePerRequestFilter {
    @Autowired
    private StringRedisTemplate redisTemplate;
    final Pattern patternId = Pattern.compile("^\\d+");
    final Pattern patternType = Pattern.compile("[a-zA-Z]+");
    private final static ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)){
            filterChain.doFilter(request,response);
            return;
        }
        String tokenInfo = JwtUtils.parseToken(token).getSubject();
        /**
         * read user from xml by token
         */
        SecurityAccount securityAccount = new SecurityAccount();
        String idStr = (String)redisTemplate.opsForHash().get(tokenInfo,"id");
        String permissionsStr = (String) redisTemplate.opsForHash().get(tokenInfo,"permissions");
        String email = (String) redisTemplate.opsForHash().get(tokenInfo,"email");
        Integer id = Integer.valueOf(idStr);
        List<String> permissions = mapper.readValue(permissionsStr, ArrayList.class);
        securityAccount.setId(id);
        securityAccount.setPermissions(permissions);
        securityAccount.setEmail(email);
        /**
         * save to context
         */
        UsernamePasswordAuthenticationToken newToken =
                new UsernamePasswordAuthenticationToken(securityAccount,null, securityAccount.getPermissions().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(newToken);
        /**
         * go on
         */
        filterChain.doFilter(request,response);
    }
}