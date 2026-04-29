package com.estoque.controller;

import com.estoque.config.TokenConfig;
import com.estoque.dto.request.LoginRequest;
import com.estoque.dto.request.RegisterUserRequest;
import com.estoque.dto.response.LoginResponse;
import com.estoque.dto.response.RegisterUserResponse;
import com.estoque.entity.UserModel;
import com.estoque.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "https://estoque-front-seven.vercel.app", allowCredentials = "true")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenConfig tokenConfig){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){

        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        UserModel userModel = (UserModel) authentication.getPrincipal();
        String token = tokenConfig.genereteToken(userModel);

        return ResponseEntity.ok(new LoginResponse(token));
    }


    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserRequest request){
        UserModel newUserModel = new UserModel();
        newUserModel.setPassword(passwordEncoder.encode(request.password()));
        newUserModel.setEmail(request.email());
        newUserModel.setName(request.name());

        userRepository.save(newUserModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterUserResponse(newUserModel.getName(), newUserModel.getEmail()));
    }

}
