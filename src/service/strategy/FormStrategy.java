package service.strategy;

import model.User;

public interface FormStrategy {
    void setUserData(User use, String password);
    boolean isRoleEditable();
}
