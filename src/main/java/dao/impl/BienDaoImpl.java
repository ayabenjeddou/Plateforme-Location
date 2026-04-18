package dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import config.HibernateUtil;
import dao.BienDao;
import model.Bien;

public class BienDaoImpl implements BienDao {

    @Override
    public Bien findById(Long id) throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Bien bien = session.get(Bien.class, id);

        session.close();

        return bien;
    }

    @Override
    public List<Bien> findAll() throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Bien> list = session
                .createQuery("FROM Bien ORDER BY nom", Bien.class)
                .list();

        session.close();

        return list;
    }

    @Override
    public void save(Bien bien) throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(bien);

        tx.commit();

        session.close();
    }

    @Override
    public void delete(Long id) throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        Bien bien = session.get(Bien.class, id);

        if (bien != null) {

            session.delete(bien);
        }

        tx.commit();

        session.close();
    }

    public List<Bien> findAvailable(LocalDateTime debut,
                                   LocalDateTime fin,
                                   Integer capaciteMin,
                                   String equipementsContains) throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();

        StringBuilder hql = new StringBuilder(
                "FROM Bien b WHERE b.active = true"
        );

        if (capaciteMin != null) {

            hql.append(" AND b.capacite >= :capaciteMin");
        }

        if (equipementsContains != null && !equipementsContains.isEmpty()) {

            hql.append(" AND b.equipements LIKE :equipements");
        }

        hql.append("""
            AND b.id NOT IN (
                SELECT r.bien.id
                FROM Reservation r
                WHERE r.statut IN ('EN_ATTENTE','VALIDEE')
                AND r.dateHeureDebut < :fin
                AND r.dateHeureFin > :debut
            )
        """);

        Query<Bien> query = session.createQuery(hql.toString(), Bien.class);

        if (capaciteMin != null) {

            query.setParameter("capaciteMin", capaciteMin);
        }

        if (equipementsContains != null && !equipementsContains.isEmpty()) {

            query.setParameter("equipements",
                    "%" + equipementsContains + "%");
        }

        query.setParameter("fin", fin);

        query.setParameter("debut", debut);

        List<Bien> list = query.list();

        session.close();

        return list;
    }
    
    @Override
    public List<Bien> findDisponibles() {

        Session session = HibernateUtil
                .getSessionFactory()
                .openSession();

        List<Bien> list = session
                .createQuery(
                        "FROM Bien WHERE active = true",
                        Bien.class
                )
                .list();

        session.close();

        return list;
    }
}