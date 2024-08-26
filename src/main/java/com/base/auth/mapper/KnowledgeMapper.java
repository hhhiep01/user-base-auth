package com.base.auth.mapper;


import com.base.auth.dto.knowledge.KnowledgeDto;
import com.base.auth.form.knowledge.CreateKnowledgeForm;
import com.base.auth.form.knowledge.UpdateKnowledgeForm;
import com.base.auth.model.Knowledge;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KnowledgeMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "publishDate", target = "publishDate")
    @BeanMapping(ignoreByDefault = true)
    @Named("fromEntityToKnowledgeDto")
    KnowledgeDto fromEntityToKnowledgeDto(Knowledge knowledge);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "publishDate", target = "publishDate")
    @BeanMapping(ignoreByDefault = true)
    @Named("fromCreateKnowledgeFormToEntityKnowledge")
    Knowledge fromCreateKnowledgeFormToEntityKnowledge(CreateKnowledgeForm createKnowledgeForm);


    @IterableMapping(elementTargetType = KnowledgeDto.class, qualifiedByName = "fromEntityToKnowledgeDto")
    List<KnowledgeDto> fromEntityToKnowledgeDtoList(List<Knowledge> addresses);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "publishDate", target = "publishDate")
    @BeanMapping(ignoreByDefault = true)
    void fromUpdateKnowledgeFormToEntity(UpdateKnowledgeForm updateKnowledgeForm, @MappingTarget Knowledge knowledge);
}