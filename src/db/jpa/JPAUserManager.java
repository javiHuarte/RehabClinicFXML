package db.jpa;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import db.interfaces.UserManager;
import pojos.users.Role;
import pojos.users.User;

public class JPAUserManager implements UserManager {

	private EntityManager em;

	@Override
	public void connect() {
		// TODO Auto-generated method stub
		em = Persistence.createEntityManagerFactory("RehabilitationClinic-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		em.close();

	}

	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();

	}

	@Override
	public void createRoles() {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		Role CEO = new Role("CEO");
		Role medicalProfessional = new Role("MedicalProfessional");
		Role recepcionist = new Role("Recepcionist");
		em.persist(CEO);
		em.persist(medicalProfessional);
		em.persist(recepcionist);
		em.getTransaction().commit();

	}



	@Override
	public Role getRole(int id) {
		// TODO Auto-generated method stub
		Query q = em.createNativeQuery("SELECT * FROM roles WHERE id = ?", Role.class);
		q.setParameter(1, id);
		Role role = (Role) q.getSingleResult();
		return role;
	}

	@Override
	public List<Role> getRoles() {
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = (List<Role>) q.getResultList();
		return roles;
	}

	@Override
	public User checkPassword(String username, String password) {
		// TODO Auto-generated method stub
		User user = null;
		try {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] hash = md.digest();
		//Create the query
		Query q = em.createNativeQuery("SELECT * FROM users WHERE username = ? AND password = ?", User.class);
		q.setParameter(1, username);
		q.setParameter(2, hash);
		user = (User) q.getSingleResult();
		} catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Role getRoleByName(String name) {
		Query q = em.createNativeQuery("SELECT * FROM roles WHERE ROLE = ?", Role.class);
		q.setParameter(1, name);
		Role role = (Role) q.getSingleResult();
		return role;
	}

}
