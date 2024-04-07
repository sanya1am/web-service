package ru.sanya1am.accountServer;

@SuppressWarnings("UnusedDeclaration")
public interface AccountServerControllerMBean {
    public int getUsersLimit();
    public void setUsersLimit(int usersLimit);
}
