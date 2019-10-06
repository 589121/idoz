package com.idoz.chuckyodis.chuckyodis.service;



import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.http.HttpMethod;
 
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.idoz.chuckyodis.chuckyodis.dao.ChuckyodisRepository;
import com.idoz.chuckyodis.chuckyodis.domain.ChuckNorrisEntety;
import com.idoz.chuckyodis.chuckyodis.domain.ChuckNorrisYodisEntety;
import com.idoz.chuckyodis.chuckyodis.domain.Factyodis;
import com.idoz.chuckyodis.chuckyodis.dto.FactyodisDto;

 


 
@Service
public class ChuckyodisServiceImpl implements ChuckyodisService{
	
	
	private MongoTemplate mongoTemplate;
	
	@Autowired
	public ChuckyodisServiceImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate=mongoTemplate;
	}
	
	@Autowired
	ChuckyodisRepository repository;
	
	
	public FactyodisDto getChuckYodis()  {
		
		Factyodis factyodis = new Factyodis();
		 
		
		/*
		 * chucknorris
		 */
		
		RestTemplate  restTemplate = new RestTemplate();
		 
		String url="https://api.chucknorris.io/jokes/random";
		UriComponentsBuilder builder=UriComponentsBuilder.fromHttpUrl(url);
		
	
		 
		
		RequestEntity<String> request= new RequestEntity<String>(HttpMethod.GET, builder.build().toUri());
		
	
		ResponseEntity<ChuckNorrisEntety> response=
						restTemplate.exchange(request, ChuckNorrisEntety.class);

	
		ChuckNorrisEntety chuckNorrisEntety =response.getBody();


		String value = chuckNorrisEntety.getValue();
System.out.println(value);
			 /*
              * Yoda
              */
               


		RestTemplate  restTemplate2 = new RestTemplate();
		
		String yodaUrl="https://api.funtranslations.com/translate/yoda.json?text="+value;
		UriComponentsBuilder builder2=UriComponentsBuilder.fromHttpUrl(yodaUrl);
		
		RequestEntity<String> request2= new RequestEntity<String>(HttpMethod.POST, builder2.build().toUri());
		
		ResponseEntity<ChuckNorrisYodisEntety> response2=
				restTemplate2.exchange(request2, ChuckNorrisYodisEntety.class);

		
		ChuckNorrisYodisEntety chuckNorrisYodisEntety =response2.getBody();
		LocalDateTime entered =LocalDateTime.now();
		
		
               
      factyodis.setEntered_date(entered);
      factyodis.setFact_text(chuckNorrisYodisEntety.getContents().getTranslated());	
      factyodis.setFact_id((int)(Math.random()*100));
		System.out.println(factyodis.getFact_text());
	 repository.save(factyodis);
		
		
		return convertToFactyodisDto(factyodis) ;
	}
	
	
	
	 
	public Set <FactyodisDto> getAllChuckYodis(int page) {
		
		
		
		
		//Pageable pageable = PageRequest.of(page, 10);
		 

		@SuppressWarnings("deprecation")
		Set <FactyodisDto> allFactyodis= repository.findAll(new PageRequest (page,10))
				.stream().map(x -> convertToFactyodisDto(x)).collect(Collectors.toSet());;
		 

		return allFactyodis;

	}


	private FactyodisDto convertToFactyodisDto(Factyodis factyodis) {
		 FactyodisDto dto = new FactyodisDto();
		 
		 dto.setFact_id(Integer.valueOf(factyodis.getFact_id()));
		 dto.setFact_text(factyodis.getFact_text());
		 dto.setEntered_date(factyodis.getEntered_date());
	
		return dto;
	}
	
	
	
	

}
