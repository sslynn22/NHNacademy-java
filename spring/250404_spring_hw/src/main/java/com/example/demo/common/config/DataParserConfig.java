package com.example.demo.common.config;

import com.example.demo.common.dataparser.CsvDataParser;
import com.example.demo.common.dataparser.DataParser;
import com.example.demo.common.dataparser.JsonDataParser;
import com.example.demo.common.properties.FileProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(FileProperties.class)
public class DataParserConfig {
    @Bean
    public DataParser dataParser(FileProperties fileProperties) {
        if ("csv".equalsIgnoreCase(fileProperties.getType())) {
            return new CsvDataParser(fileProperties);
        } else if ("json".equalsIgnoreCase(fileProperties.getType())) {
            return new JsonDataParser(fileProperties);
        }
        throw new IllegalArgumentException("Unsupported data parser type: " + fileProperties.getType());
    }
}
