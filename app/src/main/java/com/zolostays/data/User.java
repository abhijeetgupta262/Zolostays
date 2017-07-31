package com.zolostays.data;

import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Immutable model class for a User.
 */

public class User implements Serializable {

    /* =================================== Class Variable ======================================= */

    @Nullable
    private String id;
    @Nullable
    private String name;
    @Nullable
    private String phoneNumber;
    @Nullable
    private String email;
    @Nullable
    private String password;


    /* =================================== Constructors ========================================= */

    public User(String phoneNumber, String password) {
        this("", "", phoneNumber, "", password);
    }

    public User(String id, String name, String phoneNumber, String email, String password) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    /* ================================ Getter - Setter Method ================================== */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@Nullable String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    @Nullable
    public String getPassword() {
        return password;
    }

    public void setPassword(@Nullable String password) {
        this.password = password;
    }

    /* ============================= Implemented Interface Method =============================== */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return (id == user.id) &&
                name.contentEquals(user.name) &&
                email.contentEquals(user.email);
    }

    @Override
    public String toString() {
        return "User with name " + name;
    }
}
