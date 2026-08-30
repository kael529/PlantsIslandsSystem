package com.zhiyu.plantsislandssystem.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    //从application.yml里读取jwt.secret的值,赋值给secret变量
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expire-minutes}")
    private long expireMinutes;

    /**
     * 生成HMAC SHA密钥的方法
     * @return 返回一个基于secret生成的HMAC SHA密钥
     */
    private SecretKey getKey(){
        // 使用Keys工具类生成HMAC SHA密钥
        // 将secret字符串按照UTF-8编码转换为字节数组
        // 然后通过hmacShaKeyFor方法生成密钥
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 生成JWT令牌的方法
     * @param userId 用户ID,用于在令牌中标识用户
     * @return 返回生成的JWT令牌字符串
     */
    public String generateToken(Long userId){
        // 创建一个HashMap来存储JWT的声明(claims)
        Map<String,Object> claims = new HashMap<>();
        // 将用户ID添加到声明中
        claims.put("userId",userId);

        // 获取当前时间作为令牌的签发时间
        Date now = new Date();
        // 计算过期时间:当前时间加上expireMinutes分钟
        Date expire = new Date(now.getTime() + expireMinutes * 60 * 1000);

        // 使用Jwts构建器创建JWT令牌
        return Jwts.builder()
                // 设置自定义声明
                .claims(claims)
                // 设置令牌主题(通常为用户ID)
                .subject(String.valueOf(userId))
                // 设置签发时间
                .issuedAt(now)
                // 设置过期时间
                .expiration(expire)
                // 使用密钥进行签名
                .signWith(getKey())
                // 压缩并返回令牌字符串
                .compact();
    }

    /**
     * 解析JWT令牌并返回其中的声明(Claims)信息
     * 该方法使用Jwts解析器,配置验证密钥后解析令牌,最后返回载荷部分
     *
     * @param token 需要解析的JWT字符串
     * @return Claims 包含令牌中声明的对象
     */
    public Claims parseToken(String token){
        // 使用Jwts解析器构建解析器实例
        return Jwts.parser()
            // 设置用于验证令牌的密钥
                .verifyWith(getKey())
            // 构建解析器
                .build()
            // 解析签名后的声明
                .parseSignedClaims(token)
            // 获取并返回载荷部分
                .getPayload();
    }

    /**
     * 根据令牌获取用户ID
     * @param token 用户认证令牌
     * @return 用户ID的长整型值
     */
    public Long getUserId(String token){
        // 解析令牌并获取其中的userId字段,将其转换为Long类型后返回
        return parseToken(token).get("userId", Long.class);
    }

    /**
     * 验证令牌的有效性
     * @param token 需要验证的令牌字符串
     * @return 如果令牌有效返回true,否则返回false
     */
    public boolean validateToken(String token){
        try{
        // 尝试解析令牌,如果解析成功则说明令牌有效
            parseToken(token);
        // 令牌解析成功,返回true
            return true;
        } catch (Exception e) {
        // 如果解析过程中出现异常,说明令牌无效,返回false
            return false;
        }
    }

}
