package com.maluc.usecase.deleteuser;

import com.maluc.command.Command;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserCommand implements Command {

    @NotNull
    @Size(min = 1)
    private String login;
}
