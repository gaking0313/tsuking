package com.example.demo.model.csv;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"No.", "UserName"})
public class Csv {
    @JsonProperty("No.")
    private Long no;
    @JsonProperty("UserName")
    private String userName;
    
    public Csv() {}

    public Csv(Long no, String userName) {
        this.no = no;
        this.userName = userName;
    }

    // Getter/Setter
}