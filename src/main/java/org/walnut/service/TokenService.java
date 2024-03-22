package org.walnut.service;

import io.smallrye.jwt.build.Jwt;
import io.vertx.ext.auth.impl.jose.JWT;
import jakarta.inject.Singleton;

@Singleton
public class TokenService {

    public String generateToken(){
        return Jwt.issuer("jwt-token")
                .subject("user")
                .groups("admin")
                .expiresAt(System.currentTimeMillis()+3600)
                .sign();
    }
}
