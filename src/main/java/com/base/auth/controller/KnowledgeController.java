package com.base.auth.controller;


import com.base.auth.dto.ApiMessageDto;
import com.base.auth.dto.ErrorCode;
import com.base.auth.dto.ResponseListDto;
import com.base.auth.dto.knowledge.KnowledgeDto;
import com.base.auth.exception.UnauthorizationException;
import com.base.auth.form.knowledge.CreateKnowledgeForm;
import com.base.auth.form.knowledge.UpdateKnowledgeForm;
import com.base.auth.mapper.KnowledgeMapper;
import com.base.auth.model.Group;
import com.base.auth.model.Knowledge;
import com.base.auth.model.criteria.KnowledgeCriteria;
import com.base.auth.repository.KnowledgeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/knowledge")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j

public class KnowledgeController extends ABasicController {

    @Autowired
    KnowledgeRepository knowledgeRepository;
    @Autowired
    KnowledgeMapper knowledgeMapper;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('KN_C')")
    public ApiMessageDto<String> create(@Valid @RequestBody CreateKnowledgeForm createKnowledgeForm, BindingResult bindingResult) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();

        Knowledge knowledge = knowledgeMapper.fromCreateKnowledgeFormToEntityKnowledge(createKnowledgeForm);

        knowledgeRepository.save(knowledge);
        apiMessageDto.setMessage("Create Success");


        return apiMessageDto;
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('KN_L')")
    public ApiMessageDto<ResponseListDto<List<KnowledgeDto>>> getAllknowledge(KnowledgeCriteria knowledgeCriteria, Pageable pageable){
        ApiMessageDto<ResponseListDto<List<KnowledgeDto>>> apiMessageDto = new ApiMessageDto<>();
        ResponseListDto<List<KnowledgeDto>> responseListDto = new ResponseListDto<>();
        Page<Knowledge> listKnowLedge = knowledgeRepository.findAll(knowledgeCriteria.getSpecification(),pageable);
        List<KnowledgeDto> knowledgeDtos = knowledgeMapper.fromEntityToKnowledgeDtoList(listKnowLedge.getContent());
        responseListDto.setContent(knowledgeDtos);
        responseListDto.setTotalPages(listKnowLedge.getTotalPages());
        responseListDto.setTotalElements(listKnowLedge.getTotalElements());

        apiMessageDto.setData(responseListDto);
        apiMessageDto.setMessage("Get list address success");
        return apiMessageDto;
    }
    @GetMapping(value = "/get/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('KN_V')")
    public ApiMessageDto<Knowledge> get(@PathVariable("id")  Long id) {
        ApiMessageDto<Knowledge> apiMessageDto = new ApiMessageDto<>();
        Knowledge knowledge =knowledgeRepository.findById(id).orElse(null);
        apiMessageDto.setData(knowledge);
        apiMessageDto.setMessage("Get knowledge success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces= MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('KN_U')")
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateKnowledgeForm updateKnowledgeForm, BindingResult bindingResult){
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Knowledge knowledge = knowledgeRepository.findById(updateKnowledgeForm.getId()).orElse(null);
        if(knowledge == null){
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.KNOWLEDGE_ERROR_NOT_FOUND);
            apiMessageDto.setMessage("Not found knowledge");
            return apiMessageDto;
        }
        knowledgeMapper.fromUpdateKnowledgeFormToEntity(updateKnowledgeForm,knowledge);
        knowledgeRepository.save(knowledge);
        apiMessageDto.setMessage("Update Success");
        return apiMessageDto;
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('KN_D')")
    public ApiMessageDto<String> delete(@PathVariable("id") Long id){
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Knowledge knowledge = knowledgeRepository.findById(id).orElse(null);
        if(knowledge == null){
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.KNOWLEDGE_ERROR_NOT_FOUND);
            apiMessageDto.setMessage("Not found knowledge");
            return apiMessageDto;
        }
        knowledgeRepository.delete(knowledge);
        return apiMessageDto;
    }
}
