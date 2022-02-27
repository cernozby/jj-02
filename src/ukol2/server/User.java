package ukol2.server;

public class User {
    private final String name;
    private final String password;

    User (String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
