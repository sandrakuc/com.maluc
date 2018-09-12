package com.maluc.usecase.changepassword;

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
public class ChangePasswordCommand implements Command {
    @NotNull
    @Size(min = 1)
    private String login;

    @NotNull
    @Size(min = 1)
    private String oldPassword;

    @NotNull
    @Size(min = 1)
    private String newPassword;

    @NotNull
    @Size(min = 1)
    private String confirmNewPassword;
}
