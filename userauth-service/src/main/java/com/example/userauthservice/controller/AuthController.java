package com.example.userauthservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userauthservice.model.TokenReqRes;
import com.example.userauthservice.model.User;
import com.example.userauthservice.service.AuthService;
import com.example.userauthservice.util.JwtTokenUtil;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
	private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = authService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }
    
    @PostMapping("/generate-token")
    public ResponseEntity<Object> generateToken(@RequestBody User user) {
        User authenticatedUser = authService.authenticateUser(user.getUsername(), user.getPassword());
        if (authenticatedUser != null) {
        	String token = jwtTokenUtil.generateToken(user.getUsername());
			TokenReqRes tokenResponse = new TokenReqRes();
			tokenResponse.setToken(token);
			tokenResponse.setExpirationTime("360 sec");
			return ResponseEntity.ok(tokenResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    

	@PostMapping("/validate-token")
	public ResponseEntity<Object> validateToken(@RequestBody TokenReqRes tokenReqRes) {
		return ResponseEntity.ok(jwtTokenUtil.validateToken(tokenReqRes.getToken()));
	}
}
