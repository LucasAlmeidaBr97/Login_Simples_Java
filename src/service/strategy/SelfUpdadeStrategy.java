package service.strategy;

import model.User;

public class SelfUpdadeStrategy implements FormStrategy {

    @Override
    public void setUserData(User user, String password) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setUserData'");
    }

    @Override
    public boolean isRoleEditable() {
        
        throw new UnsupportedOperationException("Unimplemented method 'isRoleEditable'");
    }

}