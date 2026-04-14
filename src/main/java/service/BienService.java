package service;

import dao.BienDao;
import dao.impl.BienDaoImpl;
import model.Bien;

import java.util.List;

public class BienService {

    private BienDao bienDao = new BienDaoImpl();

    public void ajouterBien(Bien bien) {
        bien.setDisponible(true);
        bienDao.save(bien);
        System.out.println("🏠 Bien ajouté !");
    }

    public List<Bien> getDisponibles() {
        return bienDao.findDisponibles();
    }
}