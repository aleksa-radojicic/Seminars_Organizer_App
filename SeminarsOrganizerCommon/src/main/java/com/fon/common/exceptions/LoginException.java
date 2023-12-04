/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fon.common.exceptions;

/**
 * Exception indicating failure of user login.
 *
 * <p>
 * It is thrown in this situations:
 * </p>
 *
 * <ul>
 * <li>Admin is already logged in;</li>
 * <li>Admin tried to log in with wrong credentials.</li>
 * </ul>
 *
 * @author Aleksa
 * @since 0.0.1
 */
public class LoginException extends RuntimeException {

    /**
     * Non-parametric constructor.
     */
    public LoginException() {
    }

    /**
     * Constructor with message parameter.
     *
     * @param message The detail message describing the validation failure or
     * error condition.
     */
    public LoginException(String message) {
        super(message);
    }
}
