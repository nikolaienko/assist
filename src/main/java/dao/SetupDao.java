package dao;


import java.util.List;

import com.google.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import models.Users;

import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import javax.persistence.FlushModeType;

public class SetupDao {
    
    @Inject
    Provider<EntityManager> entityManagerProvider;

    @Transactional
    public void setup() {
        
        EntityManager entityManager = entityManagerProvider.get();
        
        Query q = entityManager.createQuery("SELECT x FROM Users x");
        List<Users> userses = (List<Users>) q.getResultList();
        
        if (userses.size() == 0) {

            // Create a new users and save it
            Users test = new Users("test", "test".hashCode(), "Test Test");
            entityManager.persist(test);

            entityManager.setFlushMode(FlushModeType.COMMIT);
            entityManager.flush();
        }

    }

}
