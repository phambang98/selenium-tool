package com.seleniumtool.ui.error;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class IncorrectUserNameException extends UsernameNotFoundException {
    public IncorrectUserNameException(String msg) {
        super(msg);
    }
}
