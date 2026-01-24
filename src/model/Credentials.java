package model;

import java.time.LocalDateTime;

import model.enums.EntityStatus;

public class Credentials extends BaseEntity {

    private Long id;
    private Long userId;
    private String password;
    private EntityStatus status;

    public Credentials(Long id, Long userId, String password) {
        super();
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.status = EntityStatus.ACTIVE;
    }

    public void desativate() {
        this.status = EntityStatus.INACTIVE;
        this.setUpdatedAt(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EntityStatus getStatus() {
        return status;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setStatus(EntityStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Credentials [id=" + id + ", userId=" + userId + ", password=" + password + ", status=" + status + "]";
    }

    

}
