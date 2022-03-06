package com.learn.web.rest.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;


}

