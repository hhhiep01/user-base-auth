package com.base.auth.dto.knowledge;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class KnowledgeDto {
    @ApiModelProperty(name = "id")
    private Long id;
    @ApiModelProperty(name = "name")
    private String name;
    @ApiModelProperty(name = "content")
    private String content;
    @ApiModelProperty(name = "publishDate")
    private Date publishDate;
}