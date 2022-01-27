package hacom.test.developer.config;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

@Configuration
public class MongoConfig {

	@Bean
	public MongoCustomConversions mongoCustomConversions() {
		/*return new MongoCustomConversions(
				Arrays.asList(new OffsetDateTimeReadConverter(), new OffsetDateTimeWriteConverter())); */
		
		List<Converter<?,?>> converters = new ArrayList<>();
        converters.add(ZonedDateTimeToDate.INSTANCE);
        converters.add(DateToZonedDateTime.INSTANCE);
        return new MongoCustomConversions(converters);
	}

	/*
    static class OffsetDateTimeWriteConverter implements Converter<OffsetDateTime, Date> {

        @Override
        public Date convert(OffsetDateTime source) {
            return source == null ? null : Date.from(source.toInstant().atZone(ZoneOffset.UTC).toInstant());
        }
    }

    static class OffsetDateTimeReadConverter implements Converter<Date, OffsetDateTime> {

        @Override
        public OffsetDateTime convert(Date source) {
            return source == null ? null : source.toInstant().atOffset(ZoneOffset.UTC);
        }
    }*/
    
    @ReadingConverter
    enum DateToZonedDateTime implements Converter<Date, ZonedDateTime> {

        INSTANCE;

        @Override
        public ZonedDateTime convert(Date date) {
            return date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .truncatedTo(ChronoUnit.MILLIS);
        }
    }

    @WritingConverter
    enum ZonedDateTimeToDate implements Converter<ZonedDateTime, Date> {

        INSTANCE;

        @Override
        public Date convert(ZonedDateTime zonedDateTime) {
            return Date.from(zonedDateTime.toInstant());
        }
    }

}
