/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ereadly.util;

/**
 *
 * @author Luluil Maknun
 */

import jakarta.servlet.http.HttpSession;

public class SessionUtil {

    public static boolean isLoggedIn(HttpSession session) {
        return session != null && session.getAttribute("user") != null;
    }

    public static boolean isAdmin(HttpSession session) {
        return isLoggedIn(session) && "ADMIN".equals(session.getAttribute("role"));
    }

    public static boolean isMember(HttpSession session) {
        return isLoggedIn(session) && "MEMBER".equals(session.getAttribute("role"));
    }
}

