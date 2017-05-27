package com.hamster.config;

import java.net.UnknownHostException;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

/**
 * Created by opabinia on 2017/5/14.
 */
@Configuration
@AutoConfigureAfter(MongoDataAutoConfiguration.class)
public class DbConfig {

  /**
   * mongoTemplate 属性设置.
   * @param mongoDbFactory mongoDbFactory
   * @param converter MappingMongoConverter
   * @return  MongoTemplate
   * @throws UnknownHostException UnknownHostException
   */
  @Bean
  public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MappingMongoConverter converter)
      throws UnknownHostException {
    // 移除 _class 字段
    converter.setTypeMapper(new DefaultMongoTypeMapper(null));
    return new MongoTemplate(mongoDbFactory, converter);
  }
}
