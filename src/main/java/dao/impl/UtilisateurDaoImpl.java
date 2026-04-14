package dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import config.HibernateUtil;
import dao.UtilisateurDao;
import model.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {

    public Utilisateur findById(Long id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Utilisateur u = s.get(Utilisateur.class, id);
        s.close();
        return u;
    }

    public List<Utilisateur> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Utilisateur> list = s.createQuery("from Utilisateur", Utilisateur.class).list();
        s.close();
        return list;
    }

    public void save(Utilisateur u) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.saveOrUpdate(u);
        tx.commit();
        s.close();
    }

    public void delete(Utilisateur u) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.delete(u);
        tx.commit();
        s.close();
    }

	@Override
	public Utilisateur findByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur findByLoginAndPassword(String login, String password) {
		// TODO Auto-generated method stub
		return null;
	}
}