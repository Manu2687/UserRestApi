package vw.practice.tdd.user2.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;
	private String name;
	private String email;
	
	@OneToMany( fetch = FetchType.LAZY,targetEntity = PhoneNumber.class, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id_num",referencedColumnName="user_id")
	private List<PhoneNumber> phoneNumbers;
	
	@OneToMany( fetch = FetchType.LAZY,targetEntity = Address.class, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id_num",referencedColumnName="user_id")
	private List<Address> addresses;

	
	public User() {
		super();
	}
	
	public User(String name, String email, List<PhoneNumber> phoneNumbers, List<Address> addresses) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNumbers = phoneNumbers;
		this.addresses = addresses;
	}



	public User(int id, String name, String email, List<PhoneNumber> phoneNumbers, List<Address> addresses) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNumbers = phoneNumbers;
		this.addresses = addresses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
}