package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Idea {

    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String address2;
    private String status;

    @JsonProperty("idea")
    private String opinion;

}
