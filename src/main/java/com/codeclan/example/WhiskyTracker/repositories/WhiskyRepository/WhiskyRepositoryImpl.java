package com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;


import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public class WhiskyRepositoryImpl implements WhiskyRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Transactional
    @Override
    public List<Whisky> findWhiskiesFromADistilleryOfASpecificAge(String name, int age) {

        List<Whisky> results = null;
        Criteria cr = null;

        try {

            Session session = entityManager.unwrap(Session.class);
            cr = session.createCriteria(Whisky.class);
            cr.createAlias("distillery", "distilleryAlias");
            cr.add(Restrictions.eq("distilleryAlias.name", name));
            cr.add(Restrictions.eq("age", age));

        } catch (HibernateException e){
            e.printStackTrace();
        }

        results = cr.list();
        return results;
    }

    @Transactional
    @Override
    public List<Whisky> findWhiskiesByRegion(String region) {

        List<Whisky> results = null;
        Criteria cr = null;

        try {

            Session session = entityManager.unwrap(Session.class);
            cr = session.createCriteria(Whisky.class);
            cr.createAlias("distillery", "distilleryAlias");
            cr.add(Restrictions.eq("distilleryAlias.region", region));

        } catch (HibernateException e){
            e.printStackTrace();
        }

        results = cr.list();
        return results;

    }

}



