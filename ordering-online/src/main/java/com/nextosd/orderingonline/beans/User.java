package com.nextosd.orderingonline.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	private int userId;
	
	private String userName;
	
	private String password;
	
	private int userType;
	
	private String logTime;

}
