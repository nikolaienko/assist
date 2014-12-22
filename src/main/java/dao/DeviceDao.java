package dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import models.Device;
import ninja.jpa.UnitOfWork;

import javax.persistence.EntityManager;

/**
 * Created by vlad on 12/22/14.
 */
public class DeviceDao {
    @Inject
    Provider<EntityManager> entityManagerProvider;
    @UnitOfWork
    public boolean createDevice(Device device){
        EntityManager entityManager = entityManagerProvider.get();
        entityManager.persist(device);
        return true;
    }
}
