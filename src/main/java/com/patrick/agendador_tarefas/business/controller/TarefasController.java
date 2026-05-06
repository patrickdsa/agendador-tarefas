package com.patrick.agendador_tarefas.business.controller;

import com.patrick.agendador_tarefas.business.dto.TarefasDTO;
import com.patrick.agendador_tarefas.business.service.TarefasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/tarefas")
@RequiredArgsConstructor
public class TarefasController {
    private final TarefasService tarefasService;
    @PostMapping
    public ResponseEntity<TarefasDTO> gravarTarefas (@RequestBody TarefasDTO tarefasDTO,
                                                     @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, tarefasDTO));
    }
}
