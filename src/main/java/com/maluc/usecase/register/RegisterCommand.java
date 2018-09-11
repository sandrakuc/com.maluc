package com.maluc.usecase.register;

import com.maluc.command.Command;
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
public class RegisterCommand implements Command {

    @NotNull
    @Size(min = 1)
    private String login;

    @NotNull
    @Size(min = 1)
    private String email;

    @NotNull
    @Size(min = 1)
    private String password;

    @NotNull
    @Size(min = 1)
    private String confirmPassword;

    @NotNull
    @Size(min = 1)
    private String name;

    @NotNull
    @Size(min = 1)
    private String surname;

    @Size(min = 1)
    private String phoneNumber;
}
