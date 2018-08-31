package com.maluc.user;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.List;

public class UserRepoImpl implements UserRepo {

    public static final String DELETE_USER = String.format("DELETE FROM %s.%s WHERE %s = ?",
            UserTable.SCHEMA,
            UserTable.NAME,
            UserTable.LOGIN_COLUMN_NAME);

    EntityManager entityManager = Persistence.createEntityManagerFactory("maluc").createEntityManager();

    public void save(User user) throws SQLException{

        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.flush();
        entityManager.getTransaction().commit();

    }

    public User getByLogin(String login) {
        entityManager.getTransaction().begin();
        List<User> users = entityManager.createNamedQuery(User.FIND_BY_LOGIN_QUERY_NAME)
                .setParameter(User.LOGIN_PARAM, login)
                .getResultList();
        entityManager.getTransaction().commit();

        if(users == null || users.isEmpty()) {
            return null;
        }
        else if(users.size() == 1){
            User user = users.get(0);
            return user;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public void deleteUserByLogin(String login) throws SQLException{
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery(DELETE_USER)
                .setParameter(1, login)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }

}
