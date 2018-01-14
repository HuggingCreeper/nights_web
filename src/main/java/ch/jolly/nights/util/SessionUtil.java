package ch.jolly.nights.util;

import ch.jolly.nights.entity.EventEntity;
import ch.jolly.nights.entity.UserEntity;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class SessionUtil {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static UserEntity getUser() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return (UserEntity) session.getAttribute("user");
    }

    public static EventEntity getEditEvent() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return (EventEntity) session.getAttribute("editEvent");
    }

    public static void setEditEvent(EventEntity p) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.setAttribute("editEvent", p);
    }

    public static ArrayList<UserEntity> getInvited() {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        List<UserEntity> invited = (ArrayList<UserEntity>) session.getAttribute("invited");
        if (invited == null) {
            session.setAttribute("invited", new ArrayList<UserEntity>());
        }
        return (ArrayList<UserEntity>) session.getAttribute("invited");
    }

    public static void setInvited(List<UserEntity> invited) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.setAttribute("invited", invited);
    }
}
