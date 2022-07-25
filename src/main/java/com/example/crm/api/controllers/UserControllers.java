package com.example.crm.api.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.crm.business.abstracts.UserService;
import com.example.crm.dataTransferObjects.UserDto;
import com.example.crm.entities.concretes.Role;
import com.example.crm.entities.concretes.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserControllers {
    private final UserService userService;
    @Autowired
    public UserControllers(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/save")
    ResponseEntity<?> save(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        this.userService.save(user);
        return ResponseEntity.created(uri).body("New user is added to db");
    }
    @PatchMapping("/user/delete")
    ResponseEntity<?> delete(@RequestParam Integer id) {
        this.userService.delete(id);
        return ResponseEntity.ok().body("The user is deleted.");
    }
    @PatchMapping("/user/disable")
    ResponseEntity<?> disable(@RequestParam Integer id) {
        this.userService.disable(id);
        return ResponseEntity.ok().body("The user is disabled.");
    }
    @PatchMapping("/user/activate")
    public ResponseEntity<?> activate(@RequestParam Integer id) {
        this.userService.activate(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/role/save")
    public ResponseEntity<?> saveRole(String roleName){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        this.userService.saveRole(roleName);
        return ResponseEntity.created(uri).build();
    }
    @PostMapping("/role/addToUser")
    public ResponseEntity<?> addRoleToUser(@RequestParam("username") String username, @RequestParam("roleName") String roleName){
        this.userService.addRoleToUser(username, roleName);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/userWithPassword/{username}")
    ResponseEntity<User>getUserWithPassword(@RequestParam("username") @PathVariable String username){
        return ResponseEntity.ok().body(this.userService.getUserWithPassword(username));
    }
    @GetMapping("/usersWithPassword")
    ResponseEntity<List<User>> getUsersWithPassword() {
        return ResponseEntity.ok().body(this.userService.getUsersWithPassword());
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserDto> getUser(@RequestParam("username") @PathVariable String username){
        return ResponseEntity.ok().body(this.userService.getUser(username));
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok().body(this.userService.getUsers());
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = this.userService.getUserWithPassword(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+ 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception exception){
                log.error("Error logging in: {}", exception.getMessage());
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }
        else{
            throw new RuntimeException("Refresh Token is missing.");
        }
    }

}

