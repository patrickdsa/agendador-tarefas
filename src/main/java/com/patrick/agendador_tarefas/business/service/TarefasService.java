package com.patrick.agendador_tarefas.business.service;

import com.patrick.agendador_tarefas.business.dto.TarefasDTO;
import com.patrick.agendador_tarefas.business.mapper.TarefaMapper;
import com.patrick.agendador_tarefas.infrastructure.entitys.TarefasEntity;
import com.patrick.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.patrick.agendador_tarefas.infrastructure.repository.TarefasRepository;
import com.patrick.agendador_tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefasService {
    private final TarefasRepository tarefasRepository;
    private final JwtUtil jwtUtil;
    private final TarefaMapper tarefaMapper;

    public TarefasDTO gravarTarefa (String token, TarefasDTO tarefasDTO){
        String email = jwtUtil.extractUsername(token.substring(7));
        tarefasDTO.setDataCriacao(LocalDateTime.now());
        tarefasDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        tarefasDTO.setEmailUsuario(email);

        TarefasEntity entity = tarefaMapper.paraTarefaEntity(tarefasDTO);

        return tarefaMapper.paraTarefaDTO(tarefasRepository.save(entity));
    }
}
