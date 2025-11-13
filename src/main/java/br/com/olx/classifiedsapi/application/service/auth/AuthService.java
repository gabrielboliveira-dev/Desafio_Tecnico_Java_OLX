package br.com.olx.classifiedsapi.application.service.auth;

import br.com.olx.classifiedsapi.application.config.security.JwtService;
import br.com.olx.classifiedsapi.application.dto.auth.AuthResponseDTO;
import br.com.olx.classifiedsapi.application.dto.auth.LoginDTO;
import br.com.olx.classifiedsapi.application.dto.auth.RegisterDTO;
import br.com.olx.classifiedsapi.application.exception.BusinessException;
import br.com.olx.classifiedsapi.domain.entity.User;
import br.com.olx.classifiedsapi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO register(RegisterDTO request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BusinessException("Este e-mail já está em uso.");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // HASH!

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return new AuthResponseDTO(jwtToken, user.getId(), user.getEmail());
    }

    public AuthResponseDTO authenticate(LoginDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(user);
        return new AuthResponseDTO(jwtToken, user.getId(), user.getEmail());
    }
}