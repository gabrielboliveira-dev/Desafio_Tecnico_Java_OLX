package br.com.olx.classifiedsapi.application.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AuthResponseDTO {
    private String token;
    private UUID userId;
    private String email;
}