package com.softuni._hibernatecodefirst.entities;


//•	id – Primary Key (number in range [1, 231-1])
//        •	username – Text with length between 4 and 30 symbols. Required.
//        •	password – Required field. Text with length between 6 and 50 symbols. Should contain at least:
//        o	1 lowercase letter
//        o	1 uppercase letter
//        o	1 digit
//        o	1 special symbol (!, @, #, $, %, ^, &, *, (, ), _, +, <, >, ?)
//        •	email – Required field. Text that is considered to be in format <user>@<host> where:
//        o	<user> is a sequence of letters and digits, where '.', '-' and '_' can appear between them (they cannot appear at the beginning or at the end of the sequence).
//        o	<host> is a sequence of at least two words, separated by dots '.' (dots cannot appear at the beginning or at the end of the sequence)
//        •	profile_picture – Image file (.jpeg or .png) with size maximum of 1MB
//        •	registered_on – Date and time of user registration
//        •	last_time_logged_in – Date and time of the last time the user logged in
//        •	age –  number in range [1, 120]
//        •	is_deleted – Shows whether the user is deleted or not


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "users")
public class User implements Serializable{

    //Regex for email and password validation
    //[A-Za-z0-9._%+-]+ - must have one of those characters; @[A-Za-z0-9.-]+ - then must start with @ and must have one of the
    //characters; then have . (that is escaped) and then have between 2 or 6 of [A-Za-z]
    private static final String EMAIL_VALIDATION = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    //^ - means the string starts from here; (?=.*[0-9]) - must have at least one symbol between 0 and 9; (?=.*[a-z]) - the same
    //must have one lower case letter; (?=\S+$) - not have white spaces; {6,50} - have a length between 6 and 50
    private static final String PASSWORD_VALIDATION = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,50}$";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "profile_picture", columnDefinition = "LONGBLOB")
    private byte[] profilePicture;

    @Column(name = "registered_on")
    private Date registeredOn;

    @Column(name = "last_time_logged_in")
    private Date lastTimeLoggedIn;

    @Column(name = "age")
    private Integer age;

    @Column(name = "is_deleted")
    private Boolean deleted;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username.length() < 4 || username.length() > 30){
            throw new IllegalArgumentException("Username must be between 4 and 30 characters");
        }


        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (!this.validatePattern(password, PASSWORD_VALIDATION)){
            throw new IllegalArgumentException("Invalid Password");
        }

        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!this.validatePattern(email, EMAIL_VALIDATION)){
            throw new IllegalArgumentException("Invalid email");
        }

        this.email = email;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        if (profilePicture.length > 1024 * 1024){
            throw new IllegalArgumentException("The picture is more then 1 megabyte");
        }

        this.profilePicture = profilePicture;
    }

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Date getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(Date lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        if (age < 1 || age > 120){
            throw new IllegalArgumentException("Age must be between 1 and 120");
        }

        this.age = age;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    private boolean validatePattern(String item, String pattern){
        Pattern patternValidator = Pattern.compile(pattern);
        Matcher matcher = patternValidator.matcher(item);

        if (matcher.find()){
            return true;
        }

        return false;
    }

}
