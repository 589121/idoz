package com.idoz.chuckyodis.chuckyodis.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.idoz.chuckyodis.chuckyodis.domain.*;

@Repository
public interface ChuckyodisRepository extends MongoRepository<Factyodis, Integer>{

}
