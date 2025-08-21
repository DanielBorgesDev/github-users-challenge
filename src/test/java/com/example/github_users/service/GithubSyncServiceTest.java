package com.example.github_users.service;

import com.example.github_users.domain.User;
import com.example.github_users.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class GithubSyncServiceTest {

    @Test
    void shouldSaveNewUserWhenNotExists() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        when(userRepository.existsByLogin("danielborges")).thenReturn(false);

        GithubSyncService service = new GithubSyncService(userRepository);

        User u = new User();
        u.setLogin("danielborges");
        u.setUrl("https://api.github.com/users/danielborges");

        userRepository.save(u);

        verify(userRepository, times(1)).save(any(User.class));
    }
}
