package com.company.webapp.util;

import com.company.webapp.model.User;
import com.company.webapp.to.UserTo;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class UserUtil {
    private UserUtil() {
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getUsername(), user.getPassword(), user.getLastLoginDate(), user.getLastClientIpAddress());
    }

    public static User prepareToSave(User user, HttpServletRequest request) {
        user.setUsername(user.getUsername().trim());
        user.setLastLoginDate(LocalDateTime.now());
        user.setLastClientIpAddress(UserUtil.getClientIP(request));
        return user;
    }

    private static String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
