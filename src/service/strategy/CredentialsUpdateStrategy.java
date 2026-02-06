package service.strategy;

import model.User;

public interface CredentialsUpdateStrategy {
    void updatePassword(User user, String newPassword);
}
