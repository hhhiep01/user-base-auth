package com.base.auth.dto.knowledge;

import com.base.auth.dto.ABasicAdminDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@Data
public class KnowledgeDto extends ABasicAdminDto {
    @ApiModelProperty(name = "name")
    private String name;
    @ApiModelProperty(name = "content")
    private String content;
    @ApiModelProperty(name = "publishDate")
    private Date publishDate;
}