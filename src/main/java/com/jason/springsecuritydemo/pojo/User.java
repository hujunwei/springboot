package com.jason.springsecuritydemo.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: com.jason.springsecuritydemo.pojo
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("t_user")
public class User {
    private Integer id;
    private String username;
    private String password;
}
