package by.gsu.epamlab.model.impl;

import by.gsu.epamlab.model.beans.Role;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.ifaces.IUserDAO;

import java.util.HashMap;
import java.util.Map;

public class UserImplMemory implements IUserDAO {
	private Map<String, User> users = new HashMap<>();

	public UserImplMemory() {
	    users.put("sys", new User("sys", "111", Role.ADMIN));
        users.put("boss", new User("boss", "000", Role.USER));
        users.put("user", new User("user", "123", Role.USER));
        users.put("bob", new User("bob", "1212", Role.USER));
    }

	public User getUser(String login, String password) {
	    User user = users.get(login);
        Role role;
	    if (user == null) {
	        role = Role.VISITOR;
        } else {
	        role = Role.getRole(password, user.getPassword(), user.getRole());
        }
		return new User(login, role);
	}

    public synchronized boolean addUser(String name, String password) {
	    boolean isExist = users.containsKey(name);
        if (!isExist) {
            users.put(name, new User(name, password, Role.USER));
        }
        return !isExist;
    }
}
