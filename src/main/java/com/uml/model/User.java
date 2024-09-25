package com.uml.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: JLChen
 * @since: 2024-09-25 12:59
 * @description:
 */

@Getter
@Setter
public class User {
    private Integer id;
    private String username;
    private String email;
    private String password;
}