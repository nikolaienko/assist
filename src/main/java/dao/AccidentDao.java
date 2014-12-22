package dao;

/**
 * Created by vlad on 12/22/14.
 */
import com.google.inject.Inject;
import com.google.inject.Provider;
import models.Accident;
import ninja.jpa.UnitOfWork;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
public class AccidentDao {
    @Inject
    Provider<EntityManager> entityManagerProvider;
    @UnitOfWork
    public boolean createAccident(Accident accident){
        EntityManager entityManager = entityManagerProvider.get();
        entityManager.persist(accident);
        return true;
    }

    @UnitOfWork
    public List<Accident> getAllAccidentsWithStatus(boolean b) {
        EntityManager entityManager = entityManagerProvider.get();
        Query q = entityManager.createQuery("SELECT x FROM Accident x WHERE resolveStatus = :statusParam");
        List<Accident> accidentList = q.setParameter("statusParam", b).getResultList();
        return accidentList;
    }
}
