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
@Document("key_pairs")
public class KeyPairModel {

    @Id
    private String id;

    private String publicKey;

    private String privateKey;

    private String userId;

}
