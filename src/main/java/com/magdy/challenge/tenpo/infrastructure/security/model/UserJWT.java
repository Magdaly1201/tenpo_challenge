package com.magdy.challenge.tenpo.infrastructure.security.model;

public class UserJWT {

    private Long id;
    private String RoleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleId() {
        return RoleId;
    }

    public void setRoleId(String roleId) {
        RoleId = roleId;
    }

    public UserJWT(Long id, String roleId) {
        this.id = id;
        RoleId = roleId;
    }
}
