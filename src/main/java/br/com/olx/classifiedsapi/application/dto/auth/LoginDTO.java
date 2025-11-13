package br.com.olx.classifiedsapi.application.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank(message = "O e-mail não pode estar em branco")
    @Email
    private String email;

    @NotBlank(message = "A senha não pode estar em branco")
    private String password;
}