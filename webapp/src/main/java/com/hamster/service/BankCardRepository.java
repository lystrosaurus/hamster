package com.hamster.service;

import com.hamster.entity.BankCard;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by opabinia on 2017/5/18.
 */
public interface BankCardRepository extends MongoRepository<BankCard, String> {
}
