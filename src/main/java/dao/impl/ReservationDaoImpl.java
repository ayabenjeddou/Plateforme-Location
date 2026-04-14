package dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import config.HibernateUtil;
import dao.ReservationDao;
import model.Client;
import model.Reservation;

public class ReservationDaoImpl implements ReservationDao {

    public Reservation findById(Long id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Reservation r = s.get(Reservation.class, id);
        s.close();
        return r;
    }

    public List<Reservation> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Reservation> list = s.createQuery("from Reservation", Reservation.class).list();
        s.close();
        return list;
    }

    public void save(Reservation r) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.saveOrUpdate(r);
        tx.commit();
        s.close();
    }

    public void delete(Reservation r) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.delete(r);
        tx.commit();
        s.close();
    }

    public List<Reservation> findByClient(Client client) {
        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Reservation> list = s.createQuery(
                "FROM Reservation r WHERE r.client.id = :id",
                Reservation.class)
                .setParameter("id", client.getId())
                .list();

        s.close();
        return list;
    }

    public List<Reservation> findByStatut(String statut) {
        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Reservation> list = s.createQuery(
                "FROM Reservation r WHERE r.statut = :statut",
                Reservation.class)
                .setParameter("statut", statut)
                .list();

        s.close();
        return list;
    }

    public List<Reservation> findByBien(Long idBien) {
        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Reservation> list = s.createQuery(
                "FROM Reservation r WHERE r.bien.id = :id",
                Reservation.class)
                .setParameter("id", idBien)
                .list();

        s.close();
        return list;
    }
}