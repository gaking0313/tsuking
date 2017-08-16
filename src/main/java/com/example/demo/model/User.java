package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonPropertyOrder({ "No.", "UserName" })
public class User {

    @JsonProperty("No.")
    private Long no;

    @JsonProperty("UserName")
    private String userName;

}