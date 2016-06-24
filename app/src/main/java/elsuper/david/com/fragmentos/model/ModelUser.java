package elsuper.david.com.fragmentos.model;

/**
 * Created by Andrés David García Gómez
 */
public class ModelUser {
    //Se agregan id, lastLogin, constructor sin parámetros y con 3 parámetros
    public int id;
    public String userName;
    public String password;
    public String lastLogin;

    public ModelUser() {
    }

    public ModelUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public ModelUser(String userName, String password, String lastLogin) {
        this.userName = userName;
        this.password = password;
        this.lastLogin = lastLogin;
    }
}