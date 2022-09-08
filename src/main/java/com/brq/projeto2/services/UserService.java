package com.brq.projeto2.services;


import com.brq.projeto2.domain.User;
import com.brq.projeto2.dto.UserDTO;
import com.brq.projeto2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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
    public UserDTO findById(String id) {
        try {
            Optional<User> user = repo.findById(id);
            UserDTO userDTO = new UserDTO();
            User userDataBase = user.get();
            userDTO.setName(userDataBase.getName());
            userDTO.setEmail(userDataBase.getEmail());
            return userDTO;
        }
        catch (Exception e){
            throw new ResourceNotFoundException(id);
        }
    }
    public User insert(User obj){
        return repo.insert(obj);
    }
    public void delete(String id) {
        findById(id);
        repo.deleteById(id);
    }
    public User update(User obj){
        Optional<User> newObj = repo.findById(obj.getId());
        updateData(newObj.get(), obj);
        return repo.save(newObj.get());
    }
    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }
    public User fromDTO(UserDTO objDto){
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }

}
