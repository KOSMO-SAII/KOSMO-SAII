package com.example.test.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String token;

    private LocalDateTime tokenGeneratedAt;

    public void generateToken(){
        this.token = UUID.randomUUID().toString();
        this.tokenGeneratedAt = LocalDateTime.now();
    }

    public boolean isValid(String token){
        return this.token.equals(token);
    }

    public boolean canSend(){
        return this.tokenGeneratedAt.isBefore(LocalDateTime.now().minusHours(1));
    }
}
