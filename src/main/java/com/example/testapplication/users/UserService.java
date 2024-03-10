package com.example.testapplication.users;

import com.example.testapplication.cars.Car;
import com.example.testapplication.cars.CarRepository;
import com.example.testapplication.users.dto.CreateUserDto;
import com.example.testapplication.users.exceptions.NoSuchUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private CarRepository carRepository;
    public void createUser(CreateUserDto dto){
        User newUser = new User(dto.firstName(), dto.lastName(), dto.email());
        userRepository.save(newUser);
    }
    public void deleteUser(Long id) throws NoSuchUser{
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }else{
            throw new NoSuchUser();
        }
    }
    public void deleteCars(Long id) throws NoSuchUser{
            var user = userRepository.findById(id).orElseThrow(()->new NoSuchUser());
            carRepository.deleteAll(user.getCars());
    }
    public void updateFirstname(Long id, String newName) throws NoSuchUser{
        if(userRepository.existsById(id)){
            var user = userRepository.findById(id).get();
            user.setFirstName(newName);
            userRepository.save(user);
        }else{
            throw new NoSuchUser();
        }
    }
    public void updateLastname(Long id, String newName) throws NoSuchUser{
        if(userRepository.existsById(id)){
            var user = userRepository.findById(id).get();
            user.setLastName(newName);
            userRepository.save(user);
        }else{
            throw new NoSuchUser();
        }
    }
    public void updateEmail(Long id, String email) throws NoSuchUser{
        if(userRepository.existsById(id)){
            var user = userRepository.findById(id).get();
            user.setEmail(email);
            userRepository.save(user);
        }else{
            throw new NoSuchUser();
        }
    }
    public String getDescription(Long userId){
        if(userRepository.existsById(userId)){
            var user = userRepository.findById(userId).get();
            String result = "User firstName is: "+user.getFirstName()+"\nUserLastname is:"+user.getLastName()+"\nUserEmail is:"+user.getEmail();
            result = result+"\n User have this cars \n{";
            for(Car car: user.getCars()){
                String carDescription = "(Car name is:"+car.getName()+"\nCar price is:"+car.getPrice()+")\n";
                result = result + carDescription;
            }
            return result+"}";
        }else{
            throw new NoSuchUser();
        }
    }
}
