package com.brq.projeto2.services;


import com.brq.projeto2.domain.User;
import com.brq.projeto2.repositories.UserRepository;
import com.brq.projeto2.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository repo;

    public List<User> findAll() {
        return repo.findAll();
    }

    public User findById(String id) {
       Optional<User> user = repo.findById(id);

        return  user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
}
