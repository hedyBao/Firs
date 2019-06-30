package com.baobao.hellodemo.controller.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@EntityScan
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionBO {
    private Integer roleId;
    private String roleName;
    private List<Integer> permissionIds;
    private String permissionName;


}
