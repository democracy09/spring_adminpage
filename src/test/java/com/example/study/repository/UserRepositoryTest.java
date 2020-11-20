package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    // Dependency Injection (DI)
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){

    }

    @Test
    @Transactional
    public void read(){

    }

    @Test
    public void update(){

        //update user... (query)
        Optional<User> user = userRepository.findById(2L);      //select

        user.ifPresent(selectUser->{    //select
           selectUser.setAccount("pppp");
           selectUser.setUpdatedAt(LocalDateTime.now());
           selectUser.setUpdatedBy("update method()");

           userRepository.save(selectUser);     // id 존재시 update save, id 바꾸면 그 col에 저장댐
        });
    }

    @Test
    @Transactional      // 테스트에서 다시 롤백 해줌
    public void delete(){
        Optional<User> user = userRepository.findById(3L);  //select

        Assertions.assertTrue(user.isPresent());        //true

        user.ifPresent(selectUser->{    //select
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(3L);

        Assertions.assertFalse(deleteUser.isPresent());       //false

    }
}
