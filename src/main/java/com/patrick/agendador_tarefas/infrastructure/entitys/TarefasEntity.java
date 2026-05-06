package com.patrick.agendador_tarefas.infrastructure.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.patrick.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("tarefas")
@NoArgsConstructor
@Builder
@Setter
@AllArgsConstructor
@Getter
public class TarefasEntity {
    @Id
    private String id;
    private String nomeTarefa;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEvento;
    private String emailUsuario;
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum statusNotificacaoEnum;

}
