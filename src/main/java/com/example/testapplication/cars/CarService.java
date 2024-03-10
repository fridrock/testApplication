package com.example.testapplication.cars;

import com.example.testapplication.cars.dto.CreateCarDto;
import com.example.testapplication.cars.exceptions.NoSuchCar;
import com.example.testapplication.users.User;
import com.example.testapplication.users.UserRepository;
import com.example.testapplication.users.exceptions.NoSuchUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarService {
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    public void createCar(CreateCarDto dto) throws NoSuchUser {
        User userRelated = userRepository.findById(dto.userId()).orElseThrow(()-> new NoSuchUser());
        Car newCar = new Car(dto.name(), dto.price(), userRelated);
        carRepository.save(newCar);
    }
    public void deleteCar(Long id) throws NoSuchCar{
        if(carRepository.existsById(id)){
            carRepository.deleteById(id);
        }else{
            throw new NoSuchCar();
        }
    }
    public void updateName(Long id, String newName){
        if(carRepository.existsById(id)){
            var car = carRepository.findById(id).get();
            car.setName(newName);
            carRepository.save(car);
        }else{
            throw new NoSuchCar();
        }
    }
    public void updatePrice(Long id, Double newPrice){
        if(carRepository.existsById(id)){
            var car = carRepository.findById(id).get();
            car.setPrice(newPrice);
            carRepository.save(car);
        }else{
            throw new NoSuchCar();
        }
    }
    public void updateUser(Long id, Long userId){
        if(carRepository.existsById(id)){
            var car = carRepository.findById(id).get();
            var newOwner = userRepository.findById(userId).orElseThrow(()->new NoSuchUser());
            car.setUser(newOwner);
            carRepository.save(car);
        }else{
            throw new NoSuchCar();
        }
    }
}
