package com.example.github_users.service;

import com.example.github_users.domain.Role;
import com.example.github_users.domain.User;
import com.example.github_users.repository.RoleRepository;
import com.example.github_users.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    public UserService(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Transactional(readOnly = true)
    public List<User> listUsersWithRoles() {
        return userRepo.findAll(); // já traz roles por causa do EAGER
    }

    @Transactional
    public Role createRole(String name) {
        return roleRepo.findByName(name)
                .orElseGet(() -> {
                    Role r = new Role();
                    r.setName(name);
                    return roleRepo.save(r);
                });
    }

    @Transactional
    public void assignRole(Long userId, Long roleId) {
        var user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        var role = roleRepo.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleId));

        user.getRoles().add(role);
        userRepo.save(user);
    }
}
