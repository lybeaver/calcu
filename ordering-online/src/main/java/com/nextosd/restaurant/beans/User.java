package com.nextosd.restaurant.beans;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userId;

    private String userName;

    private String password;

    private Integer userType;

    private Date logTime;

}