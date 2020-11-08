package com.example.pfwcho.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="user")
@Data
public class User {

   @Id
   @Column(name="id")
   private int id;

   @Column(name="name")
   private String name;

   @Column(name="surname")
   private String surname;


}
