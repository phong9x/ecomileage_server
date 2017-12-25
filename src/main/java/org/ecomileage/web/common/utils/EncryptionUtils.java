package org.ecomileage.web.common.utils;

import org.ecomileage.bean.UserItem;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Created by kennphong on 5/20/2016.
 */
public class EncryptionUtils {

    public static final String JWT_KEY = "Ecomileage";

    public static void main(String[] args) {
    	System.out.println(jwtBuild("1111"));
    	UserItem u = jwtParse(" eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJpZFwiOjIsXCJ1c2VybmFtZVwiOm51bGwsXCJyb2xlSWRcIjoyLFwicm9sZU5hbWVcIjpudWxsLFwiZXhwaXJhdGlvblwiOjE1MTY0MzI2MjIxMzZ9In0.Ommsx5ljaK8nev1Y-Z8YF7vgvxI_Jl5Sl_N3gCtoAMc", UserItem.class);
    System.out.println(u);
    }
    /**
     * Encrypt with jwt
     * @param subject
     * @return
     */
    public static String jwtBuild(Object subject) {
        return Jwts.builder()
                .setSubject(JsonUtils.toString(subject))
                .signWith(SignatureAlgorithm.HS256, JWT_KEY.getBytes())
                .compact();
    }

    /**
     * Decrypt with jwt
     * and convert to klass T
     * @param jwtToken
     * @param klass
     * @param <T>
     * @return
     */
    public static <T> T jwtParse(String jwtToken, Class<T> klass) {
        String parse = Jwts.parser()
                .setSigningKey(JWT_KEY.getBytes())
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();
        return JsonUtils.from(parse, klass);
    }

}