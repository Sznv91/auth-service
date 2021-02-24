package ru.atom.test.task.auth.util;

import org.springframework.stereotype.Component;
import ru.atom.test.task.auth.model.UserTo;

import java.util.regex.Pattern;

@Component
public class UserFieldChecker {

    public void checkFields(UserTo user) {
        checkName(user.getName());
        checkPhone(user.getPhone());
        checkPassword(user.getPassword(), user.getConfirmPassword());
        checkRules(user.isRules());
    }

    private void checkName(String s) {
        char[] array = s.toCharArray();

        int counter = 0;
        Pattern pattern = Pattern.compile("[а-яёА-ЯЁ]");
        for (char a : array) {
            if (!pattern.matcher(String.valueOf(a)).find()) {
                if (!String.valueOf(a).equals(" ")) {
                    throw UserFieldException.get("Not Cyrillic symbol in name");
                }
                counter--;
            } else {
                counter++;
            }

        }
        if (counter < 2) {
            throw UserFieldException.get("name length less than 2");
        }
    }

    private void checkPhone(String phone) {
        if (phone.length() != 10) {
            throw UserFieldException.get("Wrong phone number length");
        }
        if (!phone.startsWith("9")) {
            throw UserFieldException.get("Wrong starts number");
        }
    }

    private void checkPassword(String password, String confirm) {
        if (password.equals(confirm)) {
            if (password.length() < 6) {
                throw UserFieldException.get("Password length less than 6");
            }
        } else throw UserFieldException.get("Passwords must match");

    }

    private void checkRules(boolean rules) {
        if (!rules) {
            throw UserFieldException.get("rules must be true");
        }
    }
}
