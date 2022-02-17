package com.ak.companyscore.config;

import com.ak.companyscore.mongo.converter.ZonedDateTimeReadConverter;
import com.ak.companyscore.mongo.converter.ZonedDateTimeWriteConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.List;

@Configuration
public class MongoConfig {
    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(
                List.of(
                        new ZonedDateTimeReadConverter(),
                        new ZonedDateTimeWriteConverter()
                )
        );
    }
}
