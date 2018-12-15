package com.danceUpByStas.enums;

/**
 * This is the UserRoleEnum designed to keep track of user role IDs.
 * @author srevin
 */
public enum UserRoleEnum {

    INSTRUCTOR(1),
    STUDENT(2);

    private final int roleNumber;

    /**
     * The parameterized constructor.
     * @param roleNumber The user role number.
     */
    UserRoleEnum(int roleNumber) {

        this.roleNumber = roleNumber;
    }

    /**
     * This method returns the user role number.
     * @return roleNumber The user role number.
     */
    public int getRoleNumber() {
        return this.roleNumber;
    }

}
