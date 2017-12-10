package com.company.webapp;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import com.company.webapp.to.UserTo;

import java.time.LocalDateTime;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private UserTo userTo;

    public AuthorizedUser(UserTo userTo, Set<GrantedAuthority> grantedAuthorities) {
        super(userTo.getUsername(), userTo.getPassword(), grantedAuthorities);
        this.userTo = userTo;
    }

    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public Long getId() {
        return userTo.getId();
    }

    public static Long id() {
        return get().userTo.getId();
    }

    public static LocalDateTime getLastLoginDate() {
        return get().userTo.getLastLoginDate();
    }

    public static String getLastClientIpAddress() {
        return get().userTo.getLastClientIpAddress();
    }

    public void update(UserTo newTo) {
        userTo = newTo;
    }

    public UserTo getUserTo() {
        return userTo;
    }

    @Override
    public String toString() {
        return userTo.toString();
    }
}
