package com.example.testapplication.users;


import com.example.testapplication.users.dto.UpdateEmailDto;
import com.example.testapplication.users.dto.UpdateFirstnameDto;
import com.example.testapplication.users.dto.UpdateLastnameDto;
import com.example.testapplication.users.dto.CreateUserDto;
import com.example.testapplication.users.exceptions.NoSuchUser;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    @GetMapping("/{userId}")
    public ResponseEntity<String> getUserDescription(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getDescription(userId));
    }
    @PostMapping("/add")
    public ResponseEntity<String> createUser(@Valid @RequestBody CreateUserDto dto){
        userService.createUser(dto);
        return ResponseEntity.ok("User added");
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted");
    }
    @DeleteMapping("/deleteCars/{userId}")
    public ResponseEntity<String> deleteCars(@PathVariable Long userId){
        userService.deleteCars(userId);
        return ResponseEntity.ok("Cars deleted");
    }
    @PatchMapping("/update/firstname/{userId}")
    public ResponseEntity<String> updateFirstname(@PathVariable Long userId,@Valid @RequestBody UpdateFirstnameDto dto){
        userService.updateFirstname(userId, dto.firstName());
        return ResponseEntity.ok("User's firstname updated");
    }
    @PatchMapping("/update/lastname/{userId}")
    public ResponseEntity<String> updateLastname(@PathVariable Long userId, @RequestBody UpdateLastnameDto dto){
        userService.updateLastname(userId, dto.lastName());
        return ResponseEntity.ok("User's lastname updated");
    }
    @PatchMapping("/update/email/{userId}")
    public ResponseEntity<String> updateEmail(@PathVariable Long userId, @Valid @RequestBody UpdateEmailDto dto){
        userService.updateEmail(userId, dto.email());
        return ResponseEntity.ok("User's email updated");
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleException(ConstraintViolationException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getConstraintViolations().stream().map(v -> v.getMessage()).collect(Collectors.joining()));
    }
    @ExceptionHandler(NoSuchUser.class)
    public ResponseEntity<String> handleException(NoSuchUser e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
