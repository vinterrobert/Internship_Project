package com.arobs.meetups.service.prize;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrizeDto {

    @JsonProperty("prize_id")
    private int id;

    @JsonProperty("description")
    private String description;

    @JsonProperty("value")
    private int value;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
