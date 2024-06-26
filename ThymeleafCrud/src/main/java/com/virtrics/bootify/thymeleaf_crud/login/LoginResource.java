package com.virtrics.bootify.thymeleaf_crud.login;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/logins", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginResource {

    private final LoginService loginService;

    public LoginResource(final LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public ResponseEntity<List<LoginDTO>> getAllLogins() {
        return ResponseEntity.ok(loginService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoginDTO> getLogin(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(loginService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createLogin(@RequestBody @Valid final LoginDTO loginDTO) {
        final Long createdId = loginService.create(loginDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateLogin(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final LoginDTO loginDTO) {
        loginService.update(id, loginDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteLogin(@PathVariable(name = "id") final Long id) {
        loginService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
