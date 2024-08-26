package com.base.auth.form.knowledge;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@ApiModel
@Data
public class CreateKnowledgeForm {
    @NotEmpty(message = "name cant not be null")
    @ApiModelProperty(name = "name", required = true)
    private String name;
    @NotEmpty(message = "content cant not be null")
    @ApiModelProperty(name = "content", required = true)
    private String content;
    @ApiModelProperty(name ="publishDate")
    private Date publishDate;
}