package service;

import dao.ReservationDao;
import dao.impl.ReservationDaoImpl;
import model.*;

import java.time.LocalDateTime;

public class ReservationService {

    private ReservationDao reservationDao = new ReservationDaoImpl();

    public Reservation reserver(Client client, Bien bien, int nbJours) {

        if (!bien.isDisponible()) {
            throw new RuntimeException("❌ Bien non disponible !");
        }

        Reservation r = new Reservation();
        r.setClient(client);
        r.setBien(bien);
        r.setDateReservation(LocalDateTime.now());
        r.setStatut(StatutReservation.CONFIRMEE);

        // 🔥 logique métier
        double total = bien.getPrixParJour() * nbJours;
        r.setMontantTotal(total);

        // 🔒 rendre le bien indisponible
        bien.setDisponible(false);

        reservationDao.save(r);

        System.out.println("✅ Réservation confirmée !");
        return r;
    }

    public void annuler(Reservation r) {

        r.setStatut(StatutReservation.ANNULEE);

        // 🔓 rendre bien disponible
        r.getBien().setDisponible(true);

        reservationDao.save(r);

        System.out.println("❌ Réservation annulée !");
    }
}