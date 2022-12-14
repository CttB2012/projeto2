package com.brq.projeto2.services;


import com.brq.projeto2.domain.Post;
import com.brq.projeto2.domain.User;
import com.brq.projeto2.dto.UserDTO;
import com.brq.projeto2.repositories.PostRepository;
import com.brq.projeto2.repositories.UserRepository;
import com.brq.projeto2.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {


    @Autowired
    private PostRepository repo;

       public Post findById(String id) {
       Optional<Post> user = repo.findById(id);
        return  user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text){
           return repo.searchTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
           maxDate = new Date(maxDate.getTime()+ 24*60*60*1000);
           return repo.fullSearch(text, minDate, maxDate);
    }

}
