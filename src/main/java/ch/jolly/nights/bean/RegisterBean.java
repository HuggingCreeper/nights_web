package ch.jolly.nights.bean;

import ch.jolly.nights.CommonMethod;
import ch.jolly.nights.bo.PageIds;
import ch.jolly.nights.dao.UserDAO;
import ch.jolly.nights.entity.UserEntity;
import ch.jolly.nights.handler.PageHandler;
import ch.jolly.nights.handler.PasswordHandler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.Date;

@ManagedBean
@RequestScoped
public class RegisterBean implements Serializable {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private Date pageBirthday;
    private String email;


    public RegisterBean() {

    }

    public String register() {

        UserDAO.addUser(new UserEntity(email, username, PasswordHandler.hashIt(password), firstname, lastname, CommonMethod.utilToDate(pageBirthday)));
        return PageHandler.showPage(PageIds.LOGIN);
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getPageBirthday() {
        return pageBirthday;
    }

    public void setPageBirthday(Date pageBirthday) {
        this.pageBirthday = pageBirthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}