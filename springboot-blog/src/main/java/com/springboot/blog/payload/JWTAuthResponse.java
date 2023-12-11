package com.springboot.blog.payload;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JWTAuthResponse {
    private String accessToken;

    @Builder.Default
    private String tokenType = "Bearer";
}
