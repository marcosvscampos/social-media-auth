package com.roguesoft.socialmedia.auth.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private String id;

    public String toString() {
        return "[ID: " + this.getId() + "]";
    }

}
