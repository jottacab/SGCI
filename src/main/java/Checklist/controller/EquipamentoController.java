package Checklist.controller;

import Checklist.model.Equipamento;
import Checklist.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository repository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("equipamentos", repository.findAll());
        return "equipamentos"; // Nome do seu arquivo HTML de listagem
    }

    @GetMapping("/novo")
    public String novoForm() {
        return "cadastro-equipamento"; // Nome do arquivo que criamos no passo anterior
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Equipamento equipamento) {
        // Garante que todo novo equipamento comece como APTO se não for definido
        if (equipamento.getStatus() == null || equipamento.getStatus().isEmpty()) {
            equipamento.setStatus("APTO");
        }
        repository.save(equipamento);
        return "redirect:/equipamentos";
    }

    @GetMapping("/{id}/status")
    public String alterarStatus(@PathVariable Long id, @RequestParam String status) {
        Equipamento equip = repository.findById(id).orElseThrow();
        equip.setStatus(status);
        repository.save(equip);
        return "redirect:/equipamentos";
    }
}