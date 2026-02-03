package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import model.enums.EntityStatus;
import model.enums.UserRole;

public class User extends BaseEntity {

    private Long id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private UserRole userRole;
    private EntityStatus status;

    public User() {
    }

    public User(String name, String email, LocalDate birthDate, UserRole userRole, EntityStatus status) {
        super();
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.userRole = userRole;
        this.status = status;
    }

    public void updateProfile(String newName, LocalDate newBirhDate, EntityStatus newStatus) {
        this.name = newName;
        this.birthDate = newBirhDate;
        this.status = newStatus;
        this.setUpdatedAt(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public EntityStatus getStatus() {
        return status;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Usuário [ Id = " + id + ", Nome = " + name + ", E-mail = " + email + ", Data de Nascimento = "
                + birthDate + ", Nivel = "
                + userRole + ", Status = " + status + "]";
    }

}
