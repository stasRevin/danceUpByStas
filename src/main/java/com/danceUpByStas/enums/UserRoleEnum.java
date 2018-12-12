package com.danceUpByStas.enums;

public enum UserRoleEnum {

    INSTRUCTOR(1),
    STUDENT(2);

    private final int roleNumber;

    UserRoleEnum(int roleNumber) {

        this.roleNumber = roleNumber;
    }

    public int getRoleNumber()
    {
        return this.roleNumber;
    }

}
