package com.learn.web.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Relation(collectionRelation = "users", itemRelation = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel extends RepresentationModel<UserModel> {

    private Long id;

    @NotEmpty(message = "Username must not empty")
    @NotNull(message = "Username must not null")
    private String username;

    @NotEmpty(message = "Password must not empty")
    @NotNull(message = "Password must not null")
    private String password;

    @NotEmpty(message = "Email must not empty")
    @NotNull(message = "Email must not null")
    private String email;


}
