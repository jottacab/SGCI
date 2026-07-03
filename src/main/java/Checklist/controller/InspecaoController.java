package Checklist.controller;

import Checklist.model.Equipamento;
import Checklist.model.Inspecao;
import Checklist.repository.EquipamentoRepository;
import Checklist.repository.InspecaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/inspecoes")
public class InspecaoController {

    @Autowired
    private InspecaoRepository repository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("inspecoes", repository.findAll());
        return "inspecoes";
    }

    @GetMapping("/nova")
public String novaForm(Model model) {
    // Busca todos os equipamentos cadastrados no banco e envia para a página
    model.addAttribute("equipamentos", equipamentoRepository.findAll());
    return "nova-inspecao"; // Nome do seu arquivo HTML
}

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Inspecao inspecao, 
                         @RequestParam(required = false) List<String> itens,
                         @RequestParam("equipamentoId") Long equipamentoId,
                         RedirectAttributes redirectAttributes) {
        
        inspecao.setData(LocalDateTime.now());
        
        // Carrega o equipamento selecionado
        if (equipamentoId != null) {
            Equipamento equipamento = equipamentoRepository.findById(equipamentoId).orElse(null);
            if (equipamento != null) {
                inspecao.setEquipamento(equipamento);
                
                // Lógica de aprovação automática
                boolean todosItensMarcados = itens != null && itens.size() >= 12; 
                boolean semObservacoes = inspecao.getObservacoes() == null || inspecao.getObservacoes().trim().isEmpty();
                boolean aprovado = todosItensMarcados && semObservacoes;
                
                inspecao.setAprovado(aprovado);
                
                // ATUALIZAÇÃO DO STATUS DO EQUIPAMENTO
                equipamento.setStatus(aprovado ? "APTO" : "INAPTO");
                equipamento.setDataUltimaRevisao(LocalDateTime.now());
                
                equipamentoRepository.save(equipamento);
                
                // Definindo a mensagem que será exibida na página inicial de inspeções
                if (aprovado) {
                    redirectAttributes.addFlashAttribute("mensagem", "✅ Bom Trabalho! Equipamento Aprovado.");
                    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
                } else {
                    redirectAttributes.addFlashAttribute("mensagem", "⚠️ Atenção: Procurar Manutenção! Equipamento Inapto.");
                    redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
                }
            }
        }
        
        repository.save(inspecao);
        return "redirect:/inspecoes";
    }
}