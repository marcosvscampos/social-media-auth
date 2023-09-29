package com.roguesoft.socialmedia.auth.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("credentials")
public class CredentialModel {

    @Id
    private String id;

    private String userId;

    private String username;

    private String type;

    private String value;
}
