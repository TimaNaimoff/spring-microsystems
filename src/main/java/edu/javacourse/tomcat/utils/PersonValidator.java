package edu.javacourse.tomcat.utils;


import edu.javacourse.tomcat.business.Person;
import edu.javacourse.tomcat.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private  final PersonDAO personDAO;
    @Autowired
    public PersonValidator(PersonDAO personDAO){
        this.personDAO=personDAO;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person=(Person)o;
        if(personDAO.checkByEmail(person.getEmail())){
            errors.rejectValue("email","","This is not correct!");
        }

    }
}
