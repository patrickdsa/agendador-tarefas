package com.patrick.agendador_tarefas.infrastructure.repository;

import com.patrick.agendador_tarefas.infrastructure.entitys.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {
}
