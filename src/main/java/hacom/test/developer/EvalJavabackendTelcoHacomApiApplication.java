package hacom.test.developer;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.bson.BsonType;
import org.bson.codecs.BsonTypeClassMap;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import hacom.test.developer.config.YAMLConfig;
import hacom.test.developer.persistence.entity.TraceMsg;
import hacom.test.developer.persistence.repository.TraceMsgRepository;
import hacom.test.developer.utils.DocumentCodecProvider;
import hacom.test.developer.utils.ZonedDateTimeCodec;

@SpringBootApplication
@EnableReactiveMongoRepositories
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class EvalJavabackendTelcoHacomApiApplication extends AbstractReactiveMongoConfiguration implements CommandLineRunner{

	private static final Logger logger = LoggerFactory.getLogger(EvalJavabackendTelcoHacomApiApplication.class);
	
	@Autowired
    private YAMLConfig myConfig;
	
	
	@Bean
	CommandLineRunner traceMsgs(TraceMsgRepository traceMsgRepository) {

		logger.info("Ingreso a Main - traceMsgs");
		
		//ZonedDateTime myJavaUtilDate = ZonedDateTime.now();
		//Instant instant = myJavaUtilDate.toInstant();
		//OffsetDateTime odt = instant.atOffset(ZoneOffset.UTC);
		
		//logger.info("odt===>"+myJavaUtilDate.toString());
		
		return args -> {
			traceMsgRepository
					.deleteAll()
			.subscribe(null, null, () -> {

				Stream.of(new TraceMsg("11111", "06101924","2022-01-01T16:45:44.088+00:00"),
						  new TraceMsg("22222", "27041978","2022-01-05T14:30:10.088+00:00"),
						  new TraceMsg("55555", "34334333","2022-01-06T18:09.17.000+07:00"),
						  new TraceMsg("33333", "27041972","2022-01-10T11:30:10.088+00:00"),
						  new TraceMsg("44444", "27041932","2022-01-20T09:30:10.088+00:00"))
						.forEach(traceMsg -> {
					traceMsgRepository
						.save(traceMsg)
						.subscribe(System.out::println);

						});

			})
			;
		};

	}
	
	public static void main(String[] args) {
		
		logger.info("Ingreso a main.");
		
		SpringApplication app = new SpringApplication(EvalJavabackendTelcoHacomApiApplication.class);
        app.setWebApplicationType(WebApplicationType.REACTIVE);
		SpringApplication.run(EvalJavabackendTelcoHacomApiApplication.class, args);
	}

    @Bean
    public MongoClient mongoClient() {
    	
    	logger.info("Ingreso a mongoClient()");

    	CodecRegistry defaultCodecRegistry =  MongoClients.getDefaultCodecRegistry();
    	 
        Map<BsonType, Class<?>> replacements = new HashMap<BsonType, Class<?>>();
        replacements.put(BsonType.DATE_TIME, OffsetDateTime.class);
        BsonTypeClassMap bsonTypeClassMap = new BsonTypeClassMap(replacements);
        DocumentCodecProvider documentCodecProvider = new DocumentCodecProvider(bsonTypeClassMap);
        
        Codec<ZonedDateTime> zonedDateTimeCodec = new ZonedDateTimeCodec();
 
        CodecRegistry codecRegistry = CodecRegistries.
                fromRegistries(CodecRegistries.fromCodecs(zonedDateTimeCodec),
                        CodecRegistries.fromProviders(documentCodecProvider),
                        defaultCodecRegistry);
 
        MongoClientSettings mcs = MongoClientSettings.builder()
        							.codecRegistry(codecRegistry)
        							.build();

        return MongoClients.create(mcs);

    }
    
	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return "hacomDb";
	}

	@Override
	public void run(String... args) throws Exception {
        System.out.println("Database ===> : " + myConfig.getDatabase());
        System.out.println("Host     ===> : " + myConfig.getHost());
	}

}
