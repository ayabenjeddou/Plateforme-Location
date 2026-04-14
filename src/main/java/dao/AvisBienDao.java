package dao;

import java.util.List;
import model.AvisBien;

public interface AvisBienDao {
    void save(AvisBien avis);
    List<AvisBien> findByBien(Long idBien);
}