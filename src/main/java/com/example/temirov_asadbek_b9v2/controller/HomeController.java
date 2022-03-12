package com.example.temirov_asadbek_b9v2.controller;

import com.example.temirov_asadbek_b9v2.dto.UserDTO;
import com.example.temirov_asadbek_b9v2.entity.User;
import com.example.temirov_asadbek_b9v2.repository.UserRepository;
import com.example.temirov_asadbek_b9v2.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request){
        String phone = request.getParameter("phone");
        String password=request.getParameter("password");
        List<User> all = userRepository.findAll();
        User userByPhone = Services.getUserByPhone(phone, password, all);
        if (all.isEmpty()){
            return "register";
        }

        else if (userByPhone.equals(null)){
            return "register";
        }

        else if (userByPhone.getRole().equals("ADMIN")){
            return "admin-page";
        }
        else{
            return "user-page";

        }
    }

    @GetMapping("/register")
    public String register(){

        return "register-page";
    }


    @GetMapping("/register/user")
    public String registerUser(@ModelAttribute UserDTO dto, Model model){
        if (!Services.checkPhoneNumber(dto.getPhoneNumber(),userRepository.findAll())){

            return "register-page";
        }
        User user= new User();
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        userRepository.save(user);
        return "index";
    }


}
