package com.maluc.usecase.login;

import com.maluc.query.Query;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginQuery implements Query {

    @NotNull
    @Size(min = 1)
    private String login;

    @NotNull
    @Size(min = 1)
    private String password;
}
