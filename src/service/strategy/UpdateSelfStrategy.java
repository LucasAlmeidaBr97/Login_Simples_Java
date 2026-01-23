package service.strategy;

import model.User;

public class UpdateSelfStrategy implements FormStrategy {

    @Override
    public void setUserData(User user, String password) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setUserData'");
    }

    @Override
    public boolean isRoleEditable() {
        
        throw new UnsupportedOperationException("Unimplemented method 'isRoleEditable'");
    }

}