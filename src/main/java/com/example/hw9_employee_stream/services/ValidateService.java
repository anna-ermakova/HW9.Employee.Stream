package com.example.hw9_employee_stream.services;


import com.example.hw9_employee_stream.exceptions.ExistsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
@Service
public class ValidateService {
    public static String validateString(String name) throws ExistsException {
        String[] names = name.split("-");
        for (int i = 0; i < names.length; i++) {
            if (!StringUtils.isAlpha(names[i])) {
                throw new ExistsException();
            }
            names[i] = StringUtils.capitalize(names[i].toLowerCase());
        }
        return String.join("-",names);
    }
}

