package dao;

import java.util.List;
import model.Reservation;
import model.Client;

public interface ReservationDao {

    Reservation findById(Long id);

    List<Reservation> findAll();

    void save(Reservation reservation);

    void delete(Reservation reservation);

    List<Reservation> findByClient(Client client);

    List<Reservation> findByStatut(String statut);

    List<Reservation> findByBien(Long idBien);
}