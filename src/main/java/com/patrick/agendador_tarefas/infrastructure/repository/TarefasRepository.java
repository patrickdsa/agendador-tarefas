package com.patrick.agendador_tarefas.infrastructure.repository;

import com.patrick.agendador_tarefas.business.dto.TarefasDTO;
import com.patrick.agendador_tarefas.infrastructure.entitys.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {

    List<TarefasEntity> findByDataEventoBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);

    List<TarefasEntity> findByEmailUsuario (String email);
}
