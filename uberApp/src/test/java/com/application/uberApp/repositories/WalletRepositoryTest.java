package com.application.uberApp.repositories;

import com.application.uberApp.TestContainerConfiguration;
import com.application.uberApp.entities.User;
import com.application.uberApp.entities.Wallet;
import com.application.uberApp.services.Impl.WalletServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestContainerConfiguration.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WalletRepositoryTest {
    @Autowired
    private WalletRepository walletRepository;
    private Wallet wallet;


    private User user;



    @BeforeEach
    void setUp(){
//        signUpDTO=SignUpDTO.builder()
//                .name("Test 1")
//                .email("test1@gmail.com")
//                .password("Test123")
//                .build();
        user= User.builder()
                .id(1L)
                .name("Test 1")
                .email("test1@gmail.com")
                .build();
        wallet=Wallet.builder()
                .id(1L)
                .user(user)
                .balance(123.4)
                .build();

    }

    @Test
    void testFindByUser_whenUserIsPresent_thenReturnUser() {
        //Arrange
        walletRepository.save(wallet);

        //Act
        Wallet userWallet = walletRepository.findByUser(user).orElse(null);

        //Assert
        assertThat(userWallet).isNotNull();



    }
}