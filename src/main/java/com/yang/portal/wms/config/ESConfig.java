package com.yang.portal.wms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
@SuppressWarnings("all")
public class ESConfig extends ElasticsearchConfigurationSupport {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public ElasticsearchCustomConversions elasticsearchCustomConversions() {
        return new ElasticsearchCustomConversions(Arrays.asList(StringToLocalDateTimeConvert.INSTANCE, LocalDateTimeToStringConvert.INSTANCE));
    }

    @ReadingConverter
    enum StringToLocalDateTimeConvert implements Converter<String, LocalDateTime> {
        INSTANCE;

        @Override
        public LocalDateTime convert(String source) {
            return LocalDateTime.parse(source, dateTimeFormatter);
        }
    }

    @WritingConverter
    enum LocalDateTimeToStringConvert implements Converter<LocalDateTime, String> {
        INSTANCE;

        @Override
        public String convert(LocalDateTime source) {
            return dateTimeFormatter.format(source);
        }
    }


}
