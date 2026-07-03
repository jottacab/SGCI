package Checklist.controller;

import Checklist.model.ItemChecklist;
import Checklist.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens")
public class ItemController {

    @Autowired
    private ItemRepository repository;

    // Listar todos os itens
    @GetMapping
    public List<ItemChecklist> listar() {
        return repository.findAll();
    }

    // Criar um novo item
    @PostMapping
    public ItemChecklist criar(@RequestBody ItemChecklist item) {
        return repository.save(item);
    }
}