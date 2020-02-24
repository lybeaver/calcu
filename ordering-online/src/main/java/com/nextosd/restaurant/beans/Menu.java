package com.nextosd.restaurant.beans;

import com.nextosd.restaurant.beans.other.BaseBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class Menu extends BaseBean {
	
    private Integer foodId;

    private String foodName;

    private String foodType;

    private Integer foodPrice;

    private Integer foodNum;

}