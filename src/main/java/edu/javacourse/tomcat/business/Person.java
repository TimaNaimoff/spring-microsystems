package edu.javacourse.tomcat.business;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="person_id")
    private Integer id;

    @NotEmpty(message="Stupid trying,name should not be empty!")
    @Size(min=2,max=30,message="Noob,name should be between 2 and 30 characters!")
    @Column(name="name")
    private String name;
    @Min(value=0,message="Let's go,age should be greatet that 0")
    @Column(name="age")
    private int age;
    @NotEmpty(message="Stupid trying,name should not be empty!")
    @Email(message="Facepalm...")
    @Column(name="email")
    private String email;
//    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+,\\d{6}",message="Your address should be in this format:" +
//            " Country,City,Postal Code")
//    private String address;
    @OneToMany(mappedBy="person")
    private List<Book> list;

    public Person(Integer id,String name,int age,String email/*String address*/) {
        this.id=id;
        this.name=name;
        this.age=age;
        this.email=email;
//        this.address=address;
    }
    public Person(String name,int age,String email/*String address*/) {
        this.name=name;
        this.age=age;
        this.email=email;
//        this.address=address;
    }
    public Person(){}

    public Integer getId() {
        return id;
    }

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

    public void setId(Integer id) {
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

//    public String getAddress() {
//        return address;
//    }
//    public void setAddress(String address) {
//        this.address = address;
//    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
//                ", address='" + address + '\'' +
                '}';
    }

}
