package com.zoo.domain;

import java.util.Objects;

public class Zookeeper {

    private Integer id;
    private String fullName;
    private String email;
    private int experienceYears;

    public Zookeeper() {}

    public Zookeeper(Integer id, String fullName, String email, int experienceYears) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.experienceYears = experienceYears;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getExperienceYears() { return experienceYears; }
    public void setExperienceYears(int experienceYears) { this.experienceYears = experienceYears; }

    @Override
    public String toString() {
        return "Zookeeper{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", experienceYears=" + experienceYears +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Zookeeper)) return false;
        Zookeeper that = (Zookeeper) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
