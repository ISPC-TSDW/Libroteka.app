package com.example.libroteka.data;

import com.example.libroteka.data.model.LoggedInUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;
    private LoginDataSource dataSource;
    private LoggedInUser user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
    }

    // Ajustar el login para usar callback
    public void login(String username, String password, LoginCallback callback) {
        dataSource.login(username, password, new LoginDataSource.LoginCallback() {
            @Override
            public void onLoginSuccess(Result<LoggedInUser> result) {
                // Si el login fue exitoso, actualiza el usuario y notifica el éxito
                setLoggedInUser(((Result.Success<LoggedInUser>) result).getData());
                callback.onLoginSuccess(result);
            }

            @Override
            public void onLoginError(Result.Error error) {
                // Si hay un error, notifica el fallo
                callback.onLoginError(error);
            }
        });
    }

    // Interfaz de callback para manejar el login desde LoginRepository
    public interface LoginCallback {
        void onLoginSuccess(Result<LoggedInUser> result);
        void onLoginError(Result.Error error);
    }

}