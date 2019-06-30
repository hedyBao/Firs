package com.baobao.hellodemo.controller.bo;

import com.baobao.hellodemo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;
@EntityScan
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleBO  {
    private Integer userId;
    private String userName;
    private String phone;
    private List<Integer> roleIds;
    private String roleNames;
}
