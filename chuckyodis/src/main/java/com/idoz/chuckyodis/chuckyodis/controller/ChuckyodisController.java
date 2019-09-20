package com.idoz.chuckyodis.chuckyodis.controller;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.idoz.chuckyodis.chuckyodis.dto.FactyodisDto;
import com.idoz.chuckyodis.chuckyodis.service.ChuckyodisService;
import com.idoz.chuckyodis.chuckyodis.service.ChuckyodisServiceImpl;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChuckyodisController {
	
@Autowired
ChuckyodisService service;
	@GetMapping("/get")
	public FactyodisDto getChuckYodis()  {
		
		return service.getChuckYodis();
	}
	
	@GetMapping("/getAll/{page}/{page_size}")
	public Set <FactyodisDto> getAllChuckYodis(@PathVariable int page, @PathVariable int page_size) {
		
		return service.getAllChuckYodis(page,page_size);
	}

}
