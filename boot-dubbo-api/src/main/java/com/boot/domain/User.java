package com.boot.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Selegant
 */

@Data
public class User implements Serializable {
    private Integer id;
    private String userName;
    private String password;
    private Integer userType;
    private String mobile;
    private String accessToken;
}
