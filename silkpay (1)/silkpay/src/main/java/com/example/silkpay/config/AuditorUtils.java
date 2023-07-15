//package com.example.silkpay.config;
//
//import lombok.experimental.UtilityClass;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//
//import java.util.UUID;
//
//@UtilityClass
//public class AuditorUtils {
//
//    private final static String EKAP_ADMIN = "ROLE_ADMIN";
//
//    public static String getCurrentUsername() {
//        return getCurrentUser().getUsername();
//    }
//
//    public static User getCurrentUser() {
//        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    }
//
//    public static boolean hasAdminRole() {
//        User user = getCurrentUser();
//        if (user == null || user.getAuthorities() == null) {
//            return false;
//        }
//        for (GrantedAuthority authority : user.getAuthorities()) {
//            if (EKAP_ADMIN.equals(authority.getAuthority())) {
//                return true;
//            }
//        }
//        return false;
//    }
//}
