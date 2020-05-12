package db.interfaces;

import java.util.List;

import pojos.users.Role;
import pojos.users.User;

public interface UserManager {

	public void connect();
	public void disconnect();
	public void createUser (User user);
	public void createRoles ();
	public Role getRole(int id);
	public Role getRoleByName(String name);
	public List<Role> getRoles();
	public User checkPassword(String username, String password);
}
