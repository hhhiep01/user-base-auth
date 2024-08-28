package com.base.auth.form.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class UpdateProfileAdminForm {
    @ApiModelProperty(name = "phone")
    private String phone;
    @ApiModelProperty(name = "password")
    private String password;
    @NotEmpty(message = "oldPassword is required")
    @ApiModelProperty(name = "oldPassword", required = true)
    private String oldPassword;
    @NotEmpty(message = "fullName is required")
    @ApiModelProperty(name = "fullName", required = true)
    private String fullName;
    @ApiModelProperty(name = "avatarPath")
    private String avatarPath;
    @ApiModelProperty(name="citizen_ID_Card")
    private String citizenIDCard;
    @ApiModelProperty(name="issuance_Date")
    private Date issuanceDate;
}
