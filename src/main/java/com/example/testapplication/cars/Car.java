package com.example.testapplication.cars;


import com.example.testapplication.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    @NotBlank(message = "Name can't be empty")
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull( message = "Car should be bought by someone")
    private User user;
    @Column(name="price")
    @NotNull(message = "Price can't be empty")
    private Double price;
    public Car(){}
    public Car(String name, Double price, User user){
        this.name = name;
        this.price = price;
        this.user = user;
    }
}
