package com.baobao.hellodemo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    private Integer permissionId;
    private String permissionName;
    private String url;

}
