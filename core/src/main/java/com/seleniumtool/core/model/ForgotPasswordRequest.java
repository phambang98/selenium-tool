package com.seleniumtool.core.model;

import lombok.Data;

@Data
public class ForgotPasswordRequest {
    private String userName;
    private String email;
}
