package com.example.hangman.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
//@SequenceGenerator(name="my_seq",sequenceName="MY_SEQ", allocationSize= 1)

//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "your_sequence")
@SequenceGenerator(name = "my_seq", sequenceName = "MY_SEQ", allocationSize = 1)


@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "my_seq")
    private Integer id;
    private String username;
    private String password;
    private int score;
    
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
    
    
}
