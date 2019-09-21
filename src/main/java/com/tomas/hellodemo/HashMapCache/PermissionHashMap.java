package com.tomas.hellodemo.HashMapCache;

import com.tomas.hellodemo.entity.Permission;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PermissionHashMap {
    private HashMap<String, Permission> map = new HashMap<>();


    public void addPermission(String key, Permission permission){

        map.put(key,permission);

    }

    public void delePermission(String key ){
        map.remove(key);

    }

    public Permission queryPermission(String key){
//        前端使用的是permissionName查询，所以在这里把permissionName设置成key
        Permission value = map.get(key);

        return value;


    }

    public void updatePermission(String key){



    }
}

