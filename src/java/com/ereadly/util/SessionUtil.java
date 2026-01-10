package com.ereadly.util;

import com.ereadly.model.User;
import javax.servlet.http.HttpSession;

public class SessionUtil {

    private SessionUtil() {}

    public static User getUser(HttpSession session) {
        if (session == null) return null;
        return (User) session.getAttribute("user");
    }

    public static boolean isLoggedIn(HttpSession session) {
        return getUser(session) != null;
    }

    public static boolean isAdmin(HttpSession session) {
        User user = getUser(session);
        return user != null && user.isAdmin();
    }

    public static void invalidate(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
    }
}
