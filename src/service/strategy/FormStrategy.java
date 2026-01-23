package service.strategy;

import model.User;

public interface FormStrategy {
    void setUserData(User user, String password);
    boolean isRoleEditable();
}
