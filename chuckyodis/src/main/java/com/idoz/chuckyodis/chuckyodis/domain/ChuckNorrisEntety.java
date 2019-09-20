package com.idoz.chuckyodis.chuckyodis.domain;


import java.time.LocalDateTime;



import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString

public class ChuckNorrisEntety {

	
	
	String[] 	categories;
	String		created_at ;
	String		icon_url ;
	String		id ;
	String		updated_at ;
	String 		url ;
	String		value;
	
	
}
