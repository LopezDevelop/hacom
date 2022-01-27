package hacom.test.developer.persistence.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import hacom.test.developer.persistence.entity.TraceMsg;

@Repository
public interface TraceMsgRepository extends ReactiveMongoRepository<TraceMsg,ObjectId> {

}
