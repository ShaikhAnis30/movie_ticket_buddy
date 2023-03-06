package com.example.book_my_show.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "User")
@Data
@NoArgsConstructor
@Builder //coz we have to make object of this entity
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private int age;

//    @NonNull
    @Column(unique = true, nullable = false)
    private String mobile;

    private String address;

    //Parent(User)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Ticket> bookedTickets = new ArrayList<>();
    //User to Ticket mapping done

}
