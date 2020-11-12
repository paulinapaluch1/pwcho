package com.example.pfwcho.controller;

import com.example.pfwcho.entity.User;
import com.example.pfwcho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;


@Controller
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserRestController() {

    }

    @GetMapping("/users")
    public String getAllUsers(Model theModel) throws SQLException, ClassNotFoundException {
       // createTableIfNotExists();
        theModel.addAttribute("users", userRepository.findAll());
        return "users";
    }

    private void createTableIfNotExists() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://Full2020-86381:3306/pfwcho?useSSL=false&serverTimezone=UTC", "ppaluch", "ppaluch");
        Statement statement = connection.createStatement();
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet tables = dbm.getTables(null, null, "user", null);
        if (tables.next()) {
        }
        else {
            String sql2 = "CREATE TABLE pfwcho.user (`id` int(11) NOT NULL AUTO_INCREMENT, `name` varchar(45) DEFAULT NULL, `surname` varchar(45) DEFAULT NULL, PRIMARY KEY (`id`)) ";
            statement.execute(sql2);
        }
    }

    @GetMapping("/users/deleteUser")
    public String deleteUser(@RequestParam("id") Integer id, Model theModel) throws Exception {
        userRepository.deleteById(id);
        theModel.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @RequestMapping(value = "/users/showFormForAdd", method = RequestMethod.GET)
    public String showFormForAddPassword(Model theModel)   {
        theModel.addAttribute("user", new User());
        return "addUser";
    }

    @RequestMapping(value = "/users/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user,
                               Model theModel) throws Exception {
        userRepository.save(user);
        return getAllUsers(theModel);
    }

    @GetMapping("/users/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id") int id, Model theModel) {
        theModel.addAttribute("user", userRepository.findById(id));
        return "addUser";
    }


}
