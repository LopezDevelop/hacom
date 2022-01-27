package hacom.test.developer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "hacom.test.developer.persistence.repository")
public class MongoDBConfiguration {
   
//	@Override
//	protected String getDatabaseName() {
//		// TODO Auto-generated method stub
//		return "hacomDb";
//	}

}
