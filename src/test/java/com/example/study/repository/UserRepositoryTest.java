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

        String account = "Test01";
        String password = "Test01";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-2222";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user);

        Assertions.assertNotNull(newUser);

    }

    @Test
    @Transactional
    public void read(){

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        user.getOrderGroupList().stream().forEach(orderGroup -> {

            System.out.println("-------------------------장바구니-------------------------");
            System.out.println("수령인 : "+orderGroup.getRevName());
            System.out.println("수령지 : "+orderGroup.getRevAddress());
            System.out.println("총 금액 : "+orderGroup.getTotalPrice());
            System.out.println("총 수량 " + orderGroup.getTotalQuantity());

            System.out.println("-------------------------주문상세-------------------------");
            orderGroup.getOrderDetailList().forEach(orderDetail -> {

                System.out.println("파트너 사 : "+orderDetail.getItem().getPartner().getName());
                System.out.println("파트너 사 카테고리 : "+orderDetail.getItem().getPartner().getCategory().getTitle());
                System.out.println("주문한 상품 : "+orderDetail.getItem().getName());
                System.out.println("고객센터 번호 : "+orderDetail.getItem().getPartner().getCallCenter());
                System.out.println("주문의 상태 : "+orderDetail.getStatus());
                System.out.println("도착 예정 일자 : "+orderDetail.getArrivalDate());




            });
        });



        Assertions.assertNotNull(user);

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
