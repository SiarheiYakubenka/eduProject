package by.gsu.epamlab.model.beans;

public class User {
    private String name;
    private String password;
    private Role role;

    public User() {
        super();
    }

    public User(String name, Role role) {
        super();
        this.name = name;
        this.role = role;
    }

    public User(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
