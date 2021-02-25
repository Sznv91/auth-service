package ru.atom.test.task.auth.util;

import org.springframework.stereotype.Component;
import ru.atom.test.task.auth.model.UserTo;

import java.util.regex.Pattern;

@Component
public class UserFieldChecker {

    private String result;

    public String checkFields(UserTo user) {
        result = "";
        checkName(user.getName());
        checkPhone(user.getPhone());
        checkPassword(user.getPassword(), user.getConfirmPassword());
        checkRules(user.isRules());
        return result;
    }

    private void checkName(String s) {
        char[] array = s.toCharArray();

        int counter = 0;
        Pattern pattern = Pattern.compile("[а-яёА-ЯЁ]");
        for (char a : array) {
            if (!pattern.matcher(String.valueOf(a)).find()) {
                if (!String.valueOf(a).equals(" ")) {
                    result = "unsuccess: Not Cyrillic symbol in name";
                    break;
                }
                counter--;
            } else {
                counter++;
            }

        }
        if (counter < 2) {
            result = "unsuccess: name length less than 2";
        }
    }

    private void checkPhone(String phone) {
        if (result.isEmpty()) {
            if (phone.length() != 10) {
                result = "unsuccess: Wrong phone number length";
            }
            if (!phone.startsWith("9")) {
                result = "unsuccess: Wrong starts number";
            }
        }
    }

    private void checkPassword(String password, String confirm) {
        if (result.isEmpty()) {
            if (password.equals(confirm)) {
                if (password.length() < 6) {
                    result = "unsuccess: Password length less than 6";
                }
            } else result = "unsuccess: Passwords must match";
        }
    }

    private void checkRules(boolean rules) {
        if (result.isEmpty()) {
            if (!rules) {
                result = "unsuccess: rules must be true";
            }
        }
    }
}
