package com.security.jwt2.repository;

import com.security.jwt2.domain.document.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post,String> {
    public boolean existsByTitle(String title);
}
