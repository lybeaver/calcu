package com.nextosd.restaurant.beans;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveOrUpdateDataCommand {
	
	private Integer id;

	private Map<Object, Map<Object, Object>> tableMap;
	
	public SaveOrUpdateDataCommand(Map<Object, Map<Object, Object>> tableMap){
		this.tableMap = tableMap;
	}
	
}
