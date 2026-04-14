package service;

import dao.UtilisateurDao;
import dao.impl.UtilisateurDaoImpl;
import model.Utilisateur;

public class UtilisateurService {

    private UtilisateurDao userDao = new UtilisateurDaoImpl();

    public void register(Utilisateur user) {

        Utilisateur existing = userDao.findByLogin(user.getLogin());

        if (existing != null) {
            throw new RuntimeException("Login déjà utilisé !");
        }

        userDao.save(user);
        System.out.println("✅ Utilisateur créé !");
    }

    public Utilisateur login(String login, String password) {
        return userDao.findByLoginAndPassword(login, password);
    }
}