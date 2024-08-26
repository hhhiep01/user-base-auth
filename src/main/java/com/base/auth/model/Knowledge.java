package com.base.auth.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Table(name = "db_user_base_knowledge")
@EntityListeners(AuditingEntityListener.class)
@Entity
@Data
public class Knowledge  extends Auditable<String>{
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "com.base.auth.service.id.IdGenerator")
    @GeneratedValue(generator = "idGenerator")
    private Long id;

    private String name;

    private String content;

    private String knowledgeStatus;

    private Date publishDate;


}
