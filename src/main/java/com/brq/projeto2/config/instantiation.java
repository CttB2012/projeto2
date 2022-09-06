package com.brq.projeto2.config;

import com.brq.projeto2.domain.Post;
import com.brq.projeto2.domain.User;
import com.brq.projeto2.dto.AuthorDTO;
import com.brq.projeto2.dto.CommentDTO;
import com.brq.projeto2.repositories.PostRepository;
import com.brq.projeto2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;


@Configuration
public class instantiation  implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, sdf.parse("06/09/2022"), "Projeto 2", "Espero que funcione!", new AuthorDTO(alex));
        Post post2 = new Post(null, sdf.parse("06/09/2022"), "Projeto 2", "Oremos !", new AuthorDTO(alex));

        CommentDTO c1 = new CommentDTO("Ajuda aí Deus kk", sdf.parse("06/09/2022"), new AuthorDTO(bob));
        CommentDTO c2 = new CommentDTO("Tmj", sdf.parse("06/09/2022"), new AuthorDTO(alex));
        CommentDTO c3 = new CommentDTO("it's us", sdf.parse("06/09/2022"), new AuthorDTO(maria));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post1.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        alex.getPosts().addAll(Arrays.asList(post1,  post2));
        userRepository.save(alex);

    }
}
