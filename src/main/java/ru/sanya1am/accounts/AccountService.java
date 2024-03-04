package ru.sanya1am.accounts;

import java.util.Map;
import java.util.HashMap;

public class AccountService {
    private final Map<String, UserProfile> usersAccounts;

    public AccountService() {
        usersAccounts = new HashMap<>();
    }

    public void singUp(String login, String password) {
        usersAccounts.put(login, new UserProfile(login, password));
    }

    public boolean signIn(String login, String password) {
        UserProfile profile = usersAccounts.get(login);

        // Проверяем пароль зарегистрированного пользователя
        return profile != null && profile.getLogin().equals(login) && profile.getPassword().equals(password);

    }
}
