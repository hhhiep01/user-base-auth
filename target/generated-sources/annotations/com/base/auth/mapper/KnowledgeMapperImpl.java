package com.base.auth.mapper;

import com.base.auth.dto.knowledge.KnowledgeDto;
import com.base.auth.form.knowledge.CreateKnowledgeForm;
import com.base.auth.form.knowledge.UpdateKnowledgeForm;
import com.base.auth.model.Knowledge;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-26T23:02:53+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class KnowledgeMapperImpl implements KnowledgeMapper {

    @Override
    public KnowledgeDto fromEntityToKnowledgeDto(Knowledge knowledge) {
        if ( knowledge == null ) {
            return null;
        }

        KnowledgeDto knowledgeDto = new KnowledgeDto();

        if ( knowledge.getCreatedDate() != null ) {
            knowledgeDto.setCreatedDate( LocalDateTime.ofInstant( knowledge.getCreatedDate().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        knowledgeDto.setName( knowledge.getName() );
        knowledgeDto.setPublishDate( knowledge.getPublishDate() );
        knowledgeDto.setId( knowledge.getId() );
        knowledgeDto.setContent( knowledge.getContent() );
        knowledgeDto.setStatus( knowledge.getStatus() );

        return knowledgeDto;
    }

    @Override
    public Knowledge fromCreateKnowledgeFormToEntityKnowledge(CreateKnowledgeForm createKnowledgeForm) {
        if ( createKnowledgeForm == null ) {
            return null;
        }

        Knowledge knowledge = new Knowledge();

        knowledge.setName( createKnowledgeForm.getName() );
        knowledge.setPublishDate( createKnowledgeForm.getPublishDate() );
        knowledge.setContent( createKnowledgeForm.getContent() );

        return knowledge;
    }

    @Override
    public List<KnowledgeDto> fromEntityToKnowledgeDtoList(List<Knowledge> knowledgeList) {
        if ( knowledgeList == null ) {
            return null;
        }

        List<KnowledgeDto> list = new ArrayList<KnowledgeDto>( knowledgeList.size() );
        for ( Knowledge knowledge : knowledgeList ) {
            list.add( fromEntityToKnowledgeDto( knowledge ) );
        }

        return list;
    }

    @Override
    public void fromUpdateKnowledgeFormToEntity(UpdateKnowledgeForm updateKnowledgeForm, Knowledge knowledge) {
        if ( updateKnowledgeForm == null ) {
            return;
        }

        if ( updateKnowledgeForm.getName() != null ) {
            knowledge.setName( updateKnowledgeForm.getName() );
        }
        if ( updateKnowledgeForm.getPublishDate() != null ) {
            knowledge.setPublishDate( updateKnowledgeForm.getPublishDate() );
        }
        if ( updateKnowledgeForm.getContent() != null ) {
            knowledge.setContent( updateKnowledgeForm.getContent() );
        }
    }
}
