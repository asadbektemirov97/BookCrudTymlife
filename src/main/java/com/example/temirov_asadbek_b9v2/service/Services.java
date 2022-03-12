package com.example.temirov_asadbek_b9v2.service;

import com.example.temirov_asadbek_b9v2.entity.User;

import java.util.List;

public class Services {
    public static boolean checkPhoneNumber(String phoneNumber, List list){
        if (list.contains(phoneNumber)) return false;
        else if(phoneNumber.length()<13 || !(phoneNumber.startsWith("+"))){
            return false;
        }
        return true;
    }

    public static boolean checkName(String name, List all) {
        if (all.contains(name)) return false;
        else if(name.length()<1){
            return false;
        }
        return true;
    }

    public static User getUserByPhone(String phone, String password, List<User> list){
        for (User user : list) {
            if (user.getPhoneNumber().equals(phone) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
}
