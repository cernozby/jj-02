package ukol2.server;

import java.util.ArrayList;
import java.util.Optional;

public class UserManager {
    private final ArrayList<User> users = new ArrayList<>();
    private Optional <User> actualUser = Optional.empty();

    UserManager() {
        this.fillUsers();
    }

    private void fillUsers() {
        this.users.add(new User("bob", "1234"));
        this.users.add(new User("tom", "1234"));
        this.users.add(new User("adam", "1234"));
        this.users.add(new User("pepa", "1234"));
    }

    public boolean connectUSer(String name, String password) {
        Optional<User> aa = this.users.stream().
                parallel().
                filter(user -> user.getName().equals(name) && user.getPassword().equals(password)).findAny();

        if (aa.isEmpty()) {return false;}
        this.actualUser = aa;
        return true;
    }

    public boolean existUSerName(String name) {
        return this.users.stream().anyMatch(user -> user.getName().equals(name));
    }

    public Optional<User> getActualUser() {
        return actualUser;
    }

    public void setActualUser(Optional<User> actualUser) {
        this.actualUser = actualUser;
    }
}
