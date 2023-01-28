package edu.javacourse.tomcat.business;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="book_id")
    private Long id;
    @Column(name="book_name")
    @NotEmpty(message="Book's name cannot be empty!")
    @Size(min=2,max=50,message="Book's name's interval is from 2 to 50!")
    private String title;
    @Column(name="book_author")
    @NotEmpty(message="Author's name cannot be empty!")
    @Size(min=2,max=50,message="Author's name's interval is from 2 to 50!")
    private String authorName;
    @Column(name="book_year")
    @Min(value=1700,message="Year should be > 1700")
    private Integer year;
    @ManyToOne
    @JoinColumn(name="person_id",referencedColumnName = "person_id")
    private Person person;
    public Book(){

    }
    public Book(String title,String authorName,Integer year){
        this.title=title;
        this.authorName=authorName;
        this.year=year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", year=" + year +
                '}';
    }
}
