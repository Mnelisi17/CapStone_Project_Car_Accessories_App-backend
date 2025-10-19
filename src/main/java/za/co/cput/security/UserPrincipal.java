package za.co.cput.security;

import za.co.cput.domain.User;

public class UserPrincipal {
    private Long userId;
    private String email;
    private String name;
    private User.Role role;

    public UserPrincipal() {}

    public UserPrincipal(Long userId, String email, String name, User.Role role) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public Long getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public User.Role getRole() { return role; }
}////
