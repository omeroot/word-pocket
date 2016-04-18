package com.yazlab.dto;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class TokenDTO {
    @JsonProperty("userId")
    private String userId;

    public String getUserToken() {
        return userId;
    }

    public void setUserToken(String userId) {
        this.userId = userId;
    }
}
