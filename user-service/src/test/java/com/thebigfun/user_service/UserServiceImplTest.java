package com.thebigfun.user_service;


import com.thebigfun.user_service.model.Roles;
import com.thebigfun.user_service.model.User;
import com.thebigfun.user_service.repository.UserRepository;
import com.thebigfun.user_service.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private WebClient.Builder webClientBuilder;



    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {

        webClientBuilder = mock(WebClient.Builder.class);


        userService = new UserServiceImpl(userRepository, webClientBuilder);
    }

    @Test
    void createUser() {
        // Mocking user
        User user = new User();
        user.setId(1L);
        user.setUserFirstName("John");
        user.setUserLastName("Doe");
        user.setUserEmail("john.doe@example.com");
        user.setUserPassword("password123");
        user.setUserPhone("123456789");
        user.setImageData("sample");
        user.setRole(Roles.USER);

        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertEquals(user, createdUser);
    }

    @Test
    void getUserById() {
        // Mocking user
        User user = new User();
        user.setId(1L);
        user.setUserFirstName("John");
        user.setUserLastName("Doe");
        user.setUserEmail("john.doe@example.com");
        user.setUserPassword("password123");
        user.setUserPhone("123456789");
        user.setImageData("sample");
        user.setRole(Roles.USER);

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        User retrievedUser = userService.getUserById(1L);

        assertEquals(user, retrievedUser);
    }

    @Test
    void updateUser() {
        // Mocking user
        User user = new User();
        user.setId(1L);
        user.setUserFirstName("John");
        user.setUserLastName("Doe");
        user.setUserEmail("john.doe@example.com");
        user.setUserPassword("password123");
        user.setUserPhone("123456789");
        user.setImageData("sample");
        user.setRole(Roles.USER);

        User updatedUser = new User();
        user.setId(1L);
        user.setUserFirstName("John");
        user.setUserLastName("Doe");
        user.setUserEmail("john.doe@example.com");
        user.setUserPassword("password123");
        user.setUserPhone("123456789");
        user.setImageData("sample");
        user.setRole(Roles.ORGANIZER);


        when(userRepository.save(updatedUser)).thenReturn(updatedUser);


        when(userRepository.existsById(user.getId())).thenReturn(true);


        User resultUser = userService.updateUser(updatedUser);


        assertEquals(updatedUser, resultUser);

    }

    @Test
    void deleteUser() {
        Long userId = 1L;
        userService.deleteUser(userId);

        // Verifying that userRepository.deleteById was called with the correct user ID
       // verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void getAllUsers() {
        // Mocking users
        User user1 = new User();
        user1.setId(1L);
        user1.setUserFirstName("John");
        user1.setUserLastName("Doe");
        user1.setUserEmail("john.doe@example.com");
        user1.setUserPassword("password123");
        user1.setUserPhone("123456789");
        user1.setImageData("sample");
        user1.setRole(Roles.ORGANIZER);

        User user2 = new User();
        user2.setId(2L);
        user2.setUserFirstName("Jane");
        user2.setUserLastName("Doe");
        user2.setUserEmail("jane.doe@example.com");
        user2.setUserPassword("password456");
        user2.setUserPhone("987654321");
        user2.setImageData("sample");
        user2.setRole(Roles.USER);

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        when(userRepository.findAll()).thenReturn(userList);

        List<User> retrievedUsers = userService.getAllUsers();

        assertEquals(userList, retrievedUsers);
    }

    // Additional test cases can be added similarly for other methods
}


