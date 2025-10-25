package model;

public class Context {
    private final String username;
    private final Role role;

    public Context(String username, Role role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }
}
