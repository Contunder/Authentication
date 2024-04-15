package com.microservice.authentification.application;

import com.microservice.authentification.domain.gateway.JWTAuthResponse;
import com.microservice.authentification.domain.gateway.LoginDto;
import com.microservice.authentification.domain.gateway.RegisterDto;
import com.microservice.authentification.domain.gateway.UserDetailsDto;
import com.microservice.authentification.domain.usecase.Login;
import com.microservice.authentification.domain.usecase.Register;
import com.microservice.authentification.domain.usecase.UserDetails;
import com.microservice.authentification.security.CustomUserDetailsService;
import com.microservice.authentification.security.JwtAuthenticationFilter;
import com.microservice.authentification.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final Login login;
    private final Register register;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetails userDetails;

    public AuthController(Login login, Register register, JwtAuthenticationFilter jwtAuthenticationFilter, JwtTokenProvider jwtTokenProvider, UserDetails userDetails) {
        this.login = login;
        this.register = register;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetails = userDetails;
    }

    @PostMapping(value = {"/login", "/signing"})
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto) {

        return ResponseEntity.ok(new JWTAuthResponse(login.execute(loginDto), "Bearer"));
    }

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {

        return new ResponseEntity<>(register.execute(registerDto), HttpStatus.CREATED);
    }

    @GetMapping("/userdetails")
    public ResponseEntity<UserDetailsDto> userDetails(HttpServletRequest request){
        String token = jwtAuthenticationFilter.getTokenFromRequest(request);
        jwtTokenProvider.validateToken(token);
        String email = jwtTokenProvider.getUsername(token);

        return new ResponseEntity<>(userDetails.execute(email), HttpStatus.OK);
    }

}