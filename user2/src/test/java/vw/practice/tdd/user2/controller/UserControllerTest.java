package vw.practice.tdd.user2.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import vw.practice.tdd.user2.model.Address;
import vw.practice.tdd.user2.model.PhoneNumber;
import vw.practice.tdd.user2.model.User;
import vw.practice.tdd.user2.service.UserService;


@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private PhoneNumber p1 = new PhoneNumber("9599142687");
    private PhoneNumber p2 = new PhoneNumber("8130375904");

    private Address a1 = new Address("Gzbd");
    private Address a2 = new Address("Delhi");

    private User user = new User("Manu Chahar", "m.chahar2687@gmail.com", List.of(p1, p2), List.of(a1, a2));
    private User user2 = new User("Mansi Agarwal", "m8@gmail.com", List.of(p2), List.of(a1, a2));
    private User user3 = new User("Sahil Chahar", "chaharraj9@gmail.com", List.of(p2), List.of(a1));

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateUser() throws Exception {
        when(userService.saveUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(user.getName())))
                .andExpect(jsonPath("$.email", is(user.getEmail())));
    }

    @Test
    void shouldReturnAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(user, user2, user3));

        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is(user.getName())))
                .andExpect(jsonPath("$[1].name", is(user2.getName())))
                .andExpect(jsonPath("$[2].name", is(user3.getName())));
    }

    @Test
    void shouldReturnUserWhenIdIsGiven() throws Exception {
        int id = 1;
        when(userService.getUserById(id)).thenReturn(user);

        mockMvc.perform(get("/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(user.getName())))
                .andExpect(jsonPath("$.email", is(user.getEmail())));
    }

    @Test
    void shouldDeleteUserWhenIdIsGiven() throws Exception {
        int id = 1;
        doNothing().when(userService).deleteUser(id);

        mockMvc.perform(delete("/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUser(id);
    }
    
    @Test
    void shouldReturnUsersWithMultipleAddressesAndPhoneNumbers() throws Exception {
        when(userService.getUsersWithMultipleAddressesAndPhoneNumber()).thenReturn(List.of(user));

        mockMvc.perform(get("/users/multiple-address-number")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(user.getName())))
                .andExpect(jsonPath("$[0].email", is(user.getEmail())));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
