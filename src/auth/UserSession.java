package auth;

import java.time.LocalDateTime;
import java.util.UUID;

import model.enums.UserRole;

public class UserSession {

    private static final UserSession instance = new UserSession();

    private Long userId;
    private String email;
    private String currentToken;
    private UserRole userRole;
    private LocalDateTime startedAt;
    private LocalDateTime finalizedAt;

    public LocalDateTime getFinalizedAt() {
        return finalizedAt;
    }

    private UserSession() {

    }

    public static UserSession getInstance() {
        return instance;
    }

    public void startSession(Long userId, String email, UserRole role) {
        this.userId = userId;
        this.email = email;
        this.currentToken = UUID.randomUUID().toString();
        this.userRole = role;
        this.startedAt = LocalDateTime.now();
        this.finalizedAt = null;
    }

    public void stopSession() {
        this.userId = null;
        this.email = null;
        this.userRole = null;
        this.finalizedAt = LocalDateTime.now();
    }

    public boolean hasAccess(UserRole requiredRole) {
        if (currentToken == null) return false;
        return this.userRole == UserRole.ADMIN || this.userRole == requiredRole;
    }

    public boolean isLogged() {
        return userId != null;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getUserRole() {
        return userRole;
    }
}