package com.craft;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@ToString
@Component
public class Student {
    private int id;
    private String name;
    private int age;

    private Address address;

    @Autowired
    public Student(@Value("7661") int id,
                   @Value("Dagmawi Tewodros") String name,
                   @Value("22") int age,
                   Address address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }
}