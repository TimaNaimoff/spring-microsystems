package edu.javacourse.tomcat.business;
import javax.validation.constraints.*;

public class Person {
    private Long id;

    @NotEmpty(message="Stupid trying,name should not be empty!")
    @Size(min=2,max=30,message="Noob,name should be between 2 and 30 characters!")
    private String name;
    @Min(value=0,message="Let's go,age should be greatet that 0")
    private int age;
    @NotEmpty(message="Stupid trying,name should not be empty!")
    @Email(message="Facepalm...")
    private String email;
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+,\\d{6}",message="Your address should be in this format:" +
            " Country,City,Postal Code")
    private String address;
    public Person(Long id,String name,int age,String email,String address) {
        this.id = id;
        this.name=name;
        this.age=age;
        this.email=email;
        this.address=address;
    }
    public Person(String name,int age,String email,String address) {
        this.name=name;
        this.age=age;
        this.email=email;
        this.address=address;
    }
    public Person(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
