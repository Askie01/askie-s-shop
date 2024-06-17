package com.askie01.controllers;

import com.askie01.factories.HibernateSessionFactory;
import com.askie01.repositories.UserRepository;
import com.askie01.users.User;
import org.hibernate.Session;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserController {
    public boolean registrable(User user) {
        return isValid(user) && isUnique(user);
    }

    public boolean isUnique(User user) {
        final String login = user.getLogin();
        final String username = user.getUsername();
        final String email = user.getEmail();
        final String phone = user.getPhone();
        final Session session = new HibernateSessionFactory()
                .getSessionFactory()
                .openSession();

        final boolean uniqueLogin = session
                .createQuery("from User where login = :login", User.class)
                .setParameter("login", login)
                .uniqueResult() == null;
        final boolean uniqueUsername = session
                .createQuery("from User where username = :username", User.class)
                .setParameter("username", username)
                .uniqueResult() == null;
        final boolean uniqueEmail = session
                .createQuery("from User where email = :email", User.class)
                .setParameter("email", email)
                .uniqueResult() == null;
        final boolean uniquePhone = session
                .createQuery("from User where phone = :phone", User.class)
                .setParameter("phone", phone)
                .uniqueResult() == null;

        return uniqueLogin &&
                uniqueUsername &&
                uniqueEmail &&
                uniquePhone;
    }

    private boolean isValid(User user) {
        final String regex = "^[A-Z][a-zA-Z0-9]{3,25}";
        final String emailRegex = "^[a-zA-Z0-9._-]{3,40}@[a-zA-Z.]{2,15}$";
        final String phoneRegex = "^[0-9]{9}$";

        final boolean loginMatches = user.getLogin().matches(regex);
        final boolean passwordMatches = user.getPassword().matches(regex);
        final boolean usernameMatches = user.getUsername().matches(regex);
        final boolean firstNameMatches = user.getFirstName().matches(regex);
        final boolean lastNameMatches = user.getLastName().matches(regex);
        final boolean emailMatches = user.getEmail().matches(emailRegex);
        final boolean phoneMatches = user.getPhone().matches(phoneRegex);
        final boolean isValidBirthdate = isValid(user.getBirthdate().toString());

        return loginMatches &&
                passwordMatches &&
                usernameMatches &&
                firstNameMatches &&
                lastNameMatches &&
                emailMatches &&
                phoneMatches &&
                isValidBirthdate;
    }

    public boolean exists(String login, String password) {
        return new UserRepository()
                .get(login, password)
                .isPresent();
    }

    private boolean isValid(String date) {
        final String regex = "^(\\d{4})-(\\d{2})-(\\d{2})$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(date);
        final boolean dateMatches = matcher.matches();

        int year = Integer.parseInt(matcher.group(1));
        int month = Integer.parseInt(matcher.group(2));
        int day = Integer.parseInt(matcher.group(3));
        return dateMatches && isValidDate(year, month, day);
    }

    private static boolean isValidDate(int year, int month, int day) {
        if (month < 1 || month > 12) {
            return false;
        }
        if (day < 1 || day > 31) {
            return false;
        }

        // Check for months with less than 31 days
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
            return false;
        }

        // Check for February
        if (month == 2) {
            if (isLeapYear(year)) {
                return day <= 29;
            } else {
                return day <= 28;
            }
        }

        return true;
    }

    private static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            }
            return true;
        }
        return false;
    }
}