package com.learn.web.rest.modules;

import lombok.*;

import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserModule {

    private Long _id;
    private String username;
    private String password;
    private String email;

    public Optional<Long> get_id() {
        return Optional.ofNullable(this._id);
    }

}
