package com.patrick.agendador_tarefas.business.mapper;

import com.patrick.agendador_tarefas.business.dto.TarefasDTO;
import com.patrick.agendador_tarefas.infrastructure.entitys.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface TarefaMapper {
    @Mapping(source ="id", target ="id")
    TarefasEntity paraTarefaEntity (TarefasDTO tarefasDTO);

    TarefasDTO paraTarefaDTO (TarefasEntity tarefasEntity);
}
