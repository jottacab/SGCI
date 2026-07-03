package Checklist.repository;

import Checklist.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
    // Esta interface herda métodos prontos como: save(), findAll(), findById(), delete()
}