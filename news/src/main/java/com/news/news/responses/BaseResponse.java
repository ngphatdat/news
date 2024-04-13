package com.news.news.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseReponse {


    @JsonProperty("created_at")
    private Long CreatedAt;
    @JsonProperty("update_at")
    private Long updateAt;

}
