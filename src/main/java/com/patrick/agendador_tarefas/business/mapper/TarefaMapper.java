package com.patrick.agendador_tarefas.business.mapper;

import com.patrick.agendador_tarefas.business.dto.TarefasDTO;
import com.patrick.agendador_tarefas.infrastructure.entitys.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper (componentModel = "spring")
public interface TarefaMapper {
    @Mapping(source ="id", target ="id")
    @Mapping (source = "dataEvento", target = "dataEvento")
    @Mapping (source = "dataCriacao", target = "dataCriacao")
    TarefasEntity paraTarefaEntity (TarefasDTO tarefasDTO);

    TarefasDTO paraTarefaDTO (TarefasEntity tarefasEntity);

    List<TarefasEntity> paraListTarefasEntity (List<TarefasDTO> dtos);

    List<TarefasDTO> paraListTarefaDTO (List<TarefasEntity> entities);
}
