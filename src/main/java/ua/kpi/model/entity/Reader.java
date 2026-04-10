package ua.kpi.model.entity;

import java.util.Objects;

public class Reader {
    private Integer id;
    private String firstName;
    private String lastName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return Objects.equals(id, reader.id) && Objects.equals(firstName, reader.firstName) && Objects.equals(lastName, reader.lastName);
    }

    @Override
public int hashCode() {
    return Objects.hash(id, firstName, lastName);
}

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public static class Builder {
        private final Reader instance = new Reader();

        public Builder setId(Integer id) {
            instance.id = id;
            return this;
        }

        public Builder setFirstName(String firstName) {
            instance.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            instance.lastName = lastName;
            return this;
        }

        public Reader build() {
            return instance;
        }
    }

}

