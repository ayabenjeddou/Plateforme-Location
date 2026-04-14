package dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import config.HibernateUtil;
import dao.AvisBienDao;
import model.AvisBien;

public class AvisBienDaoImpl implements AvisBienDao {

    public void save(AvisBien a) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.saveOrUpdate(a);
        tx.commit();
        s.close();
    }

    public List<AvisBien> findByBien(Long idBien) {
        Session s = HibernateUtil.getSessionFactory().openSession();

        List<AvisBien> list = s.createQuery(
                "FROM AvisBien a WHERE a.idBien = :id",
                AvisBien.class)
                .setParameter("id", idBien)
                .list();

        s.close();
        return list;
    }
}