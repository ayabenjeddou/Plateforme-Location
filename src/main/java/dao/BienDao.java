package dao;

import java.util.List;
import model.Bien;

public interface BienDao {

    Bien findById(Long id);

    List<Bien> findAll();

    void save(Bien bien);

    void delete(Bien bien);

	List<Bien> findDisponibles();
}