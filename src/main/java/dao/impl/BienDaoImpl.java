package dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import config.HibernateUtil;
import dao.BienDao;
import model.Bien;

public class BienDaoImpl implements BienDao {

    public Bien findById(Long id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Bien b = s.get(Bien.class, id);
        s.close();
        return b;
    }

    public List<Bien> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Bien> list = s.createQuery("from Bien", Bien.class).list();
        s.close();
        return list;
    }

    public void save(Bien b) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.saveOrUpdate(b);
        tx.commit();
        s.close();
    }

    public void delete(Bien b) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.delete(b);
        tx.commit();
        s.close();
    }

	@Override
	public List<Bien> findDisponibles() {
		// TODO Auto-generated method stub
		return null;
	}
}