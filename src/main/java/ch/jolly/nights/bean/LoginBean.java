package ch.jolly.nights.bean;

import ch.jolly.nights.bo.PageIds;
import ch.jolly.nights.dao.UserDAO;
import ch.jolly.nights.entity.UserEntity;
import ch.jolly.nights.handler.PageHandler;
import ch.jolly.nights.handler.PasswordHandler;
import ch.jolly.nights.util.SessionUtil;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;

    private UserEntity loginuser = new UserEntity();

    public String validateUsernamePassword() {
        loginuser = UserDAO.validate(username, PasswordHandler.hashIt(password));

        if (loginuser != null) {
            HttpSession session = SessionUtil.getSession();
            session.setAttribute("user", loginuser);
            return PageHandler.showPage(PageIds.HOME);
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return PageHandler.showPage(PageIds.LOGIN);
        }
    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionUtil.getSession();
        session.invalidate();
        return PageHandler.showPage(PageIds.LOGIN);
    }

    public String register() {
        return PageHandler.showPage(PageIds.REGISTER);
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

    public UserEntity getLoginuser() {
        return loginuser;
    }

    public void setLoginuser(UserEntity loginuser) {
        this.loginuser = loginuser;
    }
}