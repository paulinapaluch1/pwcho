package com.example.pfwcho.repository;


import com.example.pfwcho.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

     List<User> findAll();

     User findById(int id);

     void deleteById(int id);


}
