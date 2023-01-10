package edu.javacourse.tomcat.business;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Email;
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

    public Person(Long id,String name,int age,String email) {
        this.id = id;
        this.name=name;
        this.age=age;
        this.email=email;
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
}
