package Checklist.repository;

import Checklist.model.Inspecao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InspecaoRepository extends JpaRepository<Inspecao, Long> {

    @Query("SELECT i FROM Inspecao i WHERE i.data >= :data ORDER BY i.data DESC")
    List<Inspecao> findLast30Days(LocalDateTime data);
    
    default List<Inspecao> findLast30Days() {
        return findLast30Days(LocalDateTime.now().minusDays(30));
    }
}