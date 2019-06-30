package com.baobao.hellodemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;
@EntityScan
@Data
//@Data注解在类上，会为类的所有属性自动生成setter/getter、equals、canEqual、hashCode、toString方法，如为final属性，
// 则不会为该属性生成setter方法。
@NoArgsConstructor
@AllArgsConstructor
//无参构造器、全参构造器。
public class UserRole {
    private Integer id;
    private String userName;
    private Integer roleId;


}
