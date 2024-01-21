package com.lcwd.user.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="micro_user")


public class User {

    @Id
    @Column(name="ID")
    private String UserId;
    @Column(name="NAME",length = 15)
    private String Name;
    @Column(name="EMAIL")

    private String email;
    @Column(name="ABOUT")

    private  String about;
//dont want to save in db use below annotation
    @Transient
private List<Rating> ratings=new ArrayList<>();
}
