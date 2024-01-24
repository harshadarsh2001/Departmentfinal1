package com.harshproject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.harshproject.controller.AuthController;
import com.harshproject.entity.AuthRequest;
import com.harshproject.entity.UserInfo;
import com.harshproject.service.AuthService;
import com.harshproject.service.JwtService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthControllerTests {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private final MockMvc mockMvc;

    public AuthControllerTests() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void addNewUser_shouldReturnSuccessMessage_whenUserIsAddedSuccessfully() throws Exception {
        // Arrange
        UserInfo userInfo = new UserInfo(/* construct your user info */);
        String successMessage = "User added successfully";

        // Mock the service call
        when(authService.addUser(any(UserInfo.class))).thenReturn(successMessage);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/new")
                .content("{" +
                        "\"username\": \"testUser\"," +
                        "\"password\": \"testPassword\"" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(successMessage));

        verify(authService, times(1)).addUser(any(UserInfo.class));
        verifyNoMoreInteractions(authService);
    }
}
