package com.emag.util;

import com.emag.exception.BadRequestException;
import com.emag.model.dto.register.RegisterRequestUserDTO;

public class UserUtility {

    public static boolean isValidPass(String password) {
        if (password == null){
            throw new BadRequestException("Password is mandatory!");
        }
        boolean result = false;
        //At least one upper case, one lower case,one digit,one special character minimum eight characters , max 20
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        if (password.matches(regex) && !password.contains(" ")) {
            result = true;
        }
        return result;
    }

    public static boolean passwordsMatch(RegisterRequestUserDTO u) {
        return u.getPassword().equals(u.getConfirmPassword());
    }

    public static boolean isValidName(String name) {
        String[] names = name.trim().split(" ");

        if (name == null){
            throw new BadRequestException("Name is mandatory!");
        }
        if (names.length != 2){
            throw new BadRequestException("Enter first and last name , separated by space!");
        }
        String specialCharacters = "#?!@$%^&*-:'{}+_()<>|[]";
        for (int i = 0; i < name.length(); i++) {
            char character = name.charAt(i);
            if (Character.isDigit(character) || specialCharacters.contains(String.valueOf(character))) {
                throw new BadRequestException("Enter correct name!");
            }
        }
        return true;
    }

    public static boolean isValidEmail(String email) {
        if (email == null) {
            throw new BadRequestException("Email is mandatory!");
        }
        boolean result = false;
        boolean emailSymbolFound = false;
        String specialCharacters = "#?!@$%^&*-:'{}+_()<>|[]";
        //Letters, numbers and "_","." before "@" symbol
        String regex = "^[A-Za-z0-9+_.]+@(.+)$";
        if (email.matches(regex)) {
            int startCharacter = 0;
            for (int i = 0; i < email.length(); i++) {
                if (email.charAt(i) == '@') {
                    if (!emailSymbolFound) {
                        startCharacter = i;
                        emailSymbolFound = true;
                    }
                }
            }
            for (int i = startCharacter + 1; i < email.length(); i++) {
                char character = email.charAt(i);
                if (!specialCharacters.contains(String.valueOf(character)) && !Character.isDigit(character) && !Character.isSpaceChar(character)) {
                    result = true;
                } else {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
