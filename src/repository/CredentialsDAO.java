package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Credentials;

public class CredentialsDAO {

    Map<Long, List<Credentials>> credentialsByUserId = new HashMap<>();

    private void initCredentials(){
        saveCredential(new Credentials(1L, 1L, "Joao.2000"));
        saveCredential(new Credentials(2l, 2L, "Carlos.2000"));
        saveCredential(new Credentials(3L, 3L, "Roberto.2000"));
        saveCredential(new Credentials(4L, 4L, "Lucas.2000"));
        saveCredential(new Credentials(5L, 1L, "Joao.2001"));
    }

     public void saveCredential(Credentials credential) {
        credentialsByUserId
                .computeIfAbsent(credential.getUserId(), id -> new ArrayList<>())
                .add(credential);
    }
    
}
