package com.service.gameservice.user.resource;

import com.service.gameservice.user.resource.util.EntityManagerProvider;
import com.service.gameservice.user.entity.User;
import com.service.gameservice.user.repository.UserRepositoryImpl;
import jakarta.inject.Inject;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserResourceIT {

    @Rule
    public EntityManagerProvider provider = EntityManagerProvider.withUnit("integration-test");

    @Inject
    UserResource userResource;

    @Mock
    UserRepositoryImpl userRepository;

    @Test
    public void testCreateNewUser_ExpectSuccess(){
        //given
        User user = new User();
        user.setUserName("a");
        user.setUserPassword("123456789");
        user.setUserEmail("aaa");

        //when mock


        //when
        userResource.createUser("a","123456789","aaa");


        User result = this.provider.em()
                .createQuery("SELECT c FROM User c", User.class)
                .getSingleResult();


        //then
        assertThat(result.getId(),equalTo(1));
        assertThat(result.getUserPassword(),equalTo(123456789));
        assertThat(result.getUserEmail(),equalTo("aaa"));

    }
}
