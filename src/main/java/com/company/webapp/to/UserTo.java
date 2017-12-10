package com.company.webapp.to;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserTo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    private LocalDateTime lastLoginDate;
    private String lastClientIpAddress;

    public UserTo() {
    }

    public UserTo(Long id, String username, String password, LocalDateTime lastLoginDate, String lastClientIpAddress) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastLoginDate = lastLoginDate;
        this.lastClientIpAddress = lastClientIpAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastClientIpAddress() {
        return lastClientIpAddress;
    }

    public void setLastClientIpAddress(String lastClientIpAddress) {
        this.lastClientIpAddress = lastClientIpAddress;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", lastLoginDate=" + lastLoginDate +
                ", lastClientIpAddress='" + lastClientIpAddress + '\'' +
                '}';
    }
}
