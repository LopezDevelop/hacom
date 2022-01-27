package hacom.test.developer.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import hacom.test.developer.EvalJavabackendTelcoHacomApiApplication;
import hacom.test.developer.config.MongoDBConfiguration;
//import hacom.test.developer.config.MongoDBConfiguration;
import hacom.test.developer.persistence.entity.TraceMsg;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = EvalJavabackendTelcoHacomApiApplication.class)
@ContextConfiguration(classes = MongoDBConfiguration.class, loader=AnnotationConfigContextLoader.class)
public class TraceMsgRepositoryTest{

    //private static final Logger logger = LoggerFactory.getLogger(TraceMsgRepositoryTest.class);
    
    //@Autowired
    //private MongoTemplate mongoTemplate;
    
	@Autowired
	private TraceMsgRepository traceMsgRepository;

	@Test
	public void registraTraceMsgTest() {

		//OffsetDateTime date1 = OffsetDateTime.now();

		Mono<TraceMsg> traceMsgMono = traceMsgRepository.save(new TraceMsg("77777", "9888888", "2022-01-23"));
		
		StepVerifier.create(traceMsgMono).assertNext(traceMsg -> assertNotNull(traceMsg.getId())).expectComplete()
				.verify();
	}


}
