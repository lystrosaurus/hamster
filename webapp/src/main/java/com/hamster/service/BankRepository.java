package com.hamster.service;

import com.hamster.entity.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by opabinia on 2017/5/15.
 */
public interface BankRepository extends MongoRepository<Bank, String> {

  Bank findByBank(String bank);

}
