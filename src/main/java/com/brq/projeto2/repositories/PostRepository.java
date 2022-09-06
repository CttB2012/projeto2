package com.brq.projeto2.repositories;



import com.brq.projeto2.domain.Post;
import com.brq.projeto2.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
