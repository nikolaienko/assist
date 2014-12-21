/**
 * Copyright (C) 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import models.Device;
import models.Users;
import ninja.jpa.UnitOfWork;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class UserDao {
    @Inject
    Provider<EntityManager> entityManagerProvider;
    @UnitOfWork
    public boolean isDeviceExist(String login, int deviceId){
        EntityManager entityManager = entityManagerProvider.get();
        Query q = entityManager.createQuery("SELECT x FROM Users x WHERE username = :usernameParam");
        Users users = (Users) q.setParameter("usernameParam", login).getSingleResult();
        Query d = entityManager.createQuery("SELECT x FROM Device x WHERE users = :userIdParam AND id =:deviceParam");
        Device device =
                (Device) d.setParameter("userIdParam", users.id).setParameter("deviceParam",deviceId).getSingleResult();
        return (device==null)?false:true;
    }
    @UnitOfWork
    public boolean pairDevice(String login, Device device){
        EntityManager entityManager = entityManagerProvider.get();
        Query q = entityManager.createQuery("SELECT x FROM Users x WHERE username = :usernameParam");
        Users users = (Users) q.setParameter("usernameParam", login).getSingleResult();
        //device.users = users.id;
        entityManager.persist(device);
        return true;
    }

    @UnitOfWork
    public boolean isUserAndPasswordValid(String username, int password) {

        if (username != null) {

            EntityManager entityManager = entityManagerProvider.get();

            Query q = entityManager.createQuery("SELECT x FROM Users x WHERE username = :usernameParam");
            Users users = (Users) q.setParameter("usernameParam", username).getSingleResult();


            if (users != null) {

                if (users.password == password) {

                    return true;
                }

            }

        }

        return false;

    }
    @UnitOfWork
    public boolean createUser(Users users){
        EntityManager entityManager = entityManagerProvider.get();
        entityManager.persist(users);
        return true;
    }
}
