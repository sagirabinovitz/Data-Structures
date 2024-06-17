package il.ac.telhai.ds.misc;

import java.util.Objects;

public class Person implements Comparable<Person>{
    String id;
    String firstName;
    String lastName;

    public Person(String id, String first, String last){
        this.id=id;
        firstName=first;
        lastName=last;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firtstName) {
        this.firstName = firtstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    public int compareTo(Person o) {
        return id.compareTo(o.id);
    }

    @Override
    public String toString(){
        return "Person [id="+ id +
                ", firstName=" + firstName +
                ", lastName=" + lastName + "]";
    }
}
