package com.example.testapplication.users;


import com.example.testapplication.cars.Car;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name = "firstname")
    @NotBlank(message="First name must be not blank")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name="email")
    @NotBlank(message = "email must be not blank")
    @Email(message = "Wrong format of email")
    private String email;
    @OneToMany(mappedBy = "user")
    @Cascade(
            {
                    org.hibernate.annotations.CascadeType.SAVE_UPDATE,
                    org.hibernate.annotations.CascadeType.REMOVE
            }
    )
    private List<Car> cars;
    public User(){}
    public User(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
