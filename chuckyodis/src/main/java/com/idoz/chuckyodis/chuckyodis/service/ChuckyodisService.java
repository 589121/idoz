package com.idoz.chuckyodis.chuckyodis.service;

import java.util.Set;


import com.idoz.chuckyodis.chuckyodis.dto.FactyodisDto;

public interface ChuckyodisService {

	FactyodisDto getChuckYodis();
	
	Set <FactyodisDto> getAllChuckYodis(int page, int page_size);
		
	
	
}
