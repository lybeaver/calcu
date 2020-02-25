package com.nextosd.restaurant.beans.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginBean {
	
	private String userName;

    private String password;
    
    private String checkNum;
    
}
