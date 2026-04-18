package dao;

import java.util.List;
import java.time.LocalDateTime;
import model.Bien;

public interface BienDao {

    Bien findById(Long id) throws Exception;

    List<Bien> findAll() throws Exception;

    void save(Bien bien) throws Exception; // insert ou update

    void delete(Long id) throws Exception;

    static List<Bien> findAvailable(LocalDateTime debut,
                             LocalDateTime fin,
                             Integer capaciteMin,
                             String equipementsContains) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
    List<Bien> findDisponibles();
}