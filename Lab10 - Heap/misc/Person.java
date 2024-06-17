package il.ac.telhai.ds.misc;

import java.util.Objects;

public class Person implements Comparable<Person> {
	String id;
	String firstName;
	String lastName;

	public Person(String id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
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

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public int compareTo(Person o) {
		return this.hashCode()-o.hashCode();
	}
}
