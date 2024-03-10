package com.example.testapplication.cars;


import com.example.testapplication.cars.dto.CreateCarDto;
import com.example.testapplication.cars.dto.UpdateNameDto;
import com.example.testapplication.cars.dto.UpdatePriceDto;
import com.example.testapplication.cars.dto.UpdateUserDto;
import com.example.testapplication.cars.exceptions.NoSuchCar;
import com.example.testapplication.users.exceptions.NoSuchUser;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    @PostMapping("/add")
    public ResponseEntity<String> createCar(@Valid @RequestBody CreateCarDto carDto) throws NoSuchUser {
        carService.createCar(carDto);
        return ResponseEntity.ok("Car added");
    }
    @DeleteMapping("/delete/{carId}")
    public ResponseEntity<String> deleteCar(@PathVariable Long carId){
        carService.deleteCar(carId);
        return ResponseEntity.ok("Car deleted");
    }
    @PatchMapping("/update/name/{carId}")
    public ResponseEntity<String> updateName(@PathVariable Long carId, @Valid @RequestBody UpdateNameDto updateNameDto){
        carService.updateName(carId, updateNameDto.name());
        return ResponseEntity.ok("Car's name updated");
    }
    @PatchMapping("/update/price/{carId}")
    public ResponseEntity<String> updatePrice(@PathVariable Long carId, @Valid @RequestBody UpdatePriceDto updatePriceDto){
        carService.updatePrice(carId, updatePriceDto.price());
        return ResponseEntity.ok("Car's price updated");
    }
    @PatchMapping("/update/user/{carId}")
    public ResponseEntity<String> updateUser(@PathVariable Long carId, @Valid @RequestBody UpdateUserDto updateUserDto){
        carService.updateUser(carId, updateUserDto.userId());
        return ResponseEntity.ok("Car's user updated");
    }
    @ExceptionHandler(NoSuchUser.class)
    public ResponseEntity<String> handleException(NoSuchUser e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(NoSuchCar.class)
    public ResponseEntity<String> handleException(NoSuchCar e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleException(ConstraintViolationException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getConstraintViolations().stream().map(v -> v.getMessage()).collect(Collectors.joining()));
    }
}
