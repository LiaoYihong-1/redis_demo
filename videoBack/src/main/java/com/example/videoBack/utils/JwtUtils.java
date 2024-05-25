package com.example.videoBack.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;

public class JwtUtils {
    private static final int EXPIRE_TIME = 30 * 60 * 1000;//默认30分钟
    private static final String TOKEN_SECRET = "privateKey";
    public static String createToken(String subject)
    {
        //time
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,EXPIRE_TIME);
        return Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET)
                .setExpiration(instance.getTime())
                .compact();
    }
    public static Claims parseToken(String token){
        return  Jwts
                .parser()
                .setSigningKey(TOKEN_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}