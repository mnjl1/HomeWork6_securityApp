package goit.springhomework.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogging(String username, String password);

}
