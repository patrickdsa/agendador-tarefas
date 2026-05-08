package com.patrick.agendador_tarefas.business.service;

import com.patrick.agendador_tarefas.business.dto.TarefasDTO;
import com.patrick.agendador_tarefas.business.mapper.TarefaMapper;
import com.patrick.agendador_tarefas.business.mapper.TarefaUpdateMapper;
import com.patrick.agendador_tarefas.infrastructure.entitys.TarefasEntity;
import com.patrick.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.patrick.agendador_tarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.patrick.agendador_tarefas.infrastructure.repository.TarefasRepository;
import com.patrick.agendador_tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {
    private final TarefasRepository tarefasRepository;
    private final JwtUtil jwtUtil;
    private final TarefaMapper tarefaMapper;
    private final TarefaUpdateMapper tarefaUpdateMapper;

    public TarefasDTO gravarTarefa(String token, TarefasDTO tarefasDTO) {
        String email = jwtUtil.extractUsername(token.substring(7));
        tarefasDTO.setDataCriacao(LocalDateTime.now());
        tarefasDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        tarefasDTO.setEmailUsuario(email);

        TarefasEntity entity = tarefaMapper.paraTarefaEntity(tarefasDTO);

        return tarefaMapper.paraTarefaDTO(tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefaMapper.paraListTarefaDTO(tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefasDTO> buscaTarefasPorEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));

        return tarefaMapper.paraListTarefaDTO(tarefasRepository.findByEmailUsuario(email));

    }

    public void deletaTarefaPorId(String id) {
        tarefasRepository.deleteById(id);
    }

    public TarefasDTO alterarStatusTarefa(StatusNotificacaoEnum status, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Tarefa não encontrada " + id));
            entity.setStatusNotificacaoEnum(status);
            return tarefaMapper.paraTarefaDTO(tarefasRepository.save(entity));

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar Status da tarefa "  + e.getCause());
        }

    }

    public TarefasDTO updateTarefa(TarefasDTO tarefasDTO, String id){
        try{
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(
                    "Tarefa não encontrada" + id));
            tarefaUpdateMapper.updateTarefas(tarefasDTO, entity);
            return tarefaMapper.paraTarefaDTO(tarefasRepository.save(entity));
        }
        catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao alterar Tarefa" + e.getCause());
        }

    }
}