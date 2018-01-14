package ch.jolly.nights.handler;

import ch.jolly.nights.bo.PageIds;
import ch.jolly.nights.util.SessionUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@RequestScoped
@ManagedBean
public class PageHandler implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String showPage(PageIds pageId) {
        if (SessionUtil.getUser() == null) {
            switch (pageId) {
                case LOGIN:
                    return "login?faces-redirect=true";
                case REGISTER:
                    return "register?faces-redirect=true";
            }
            return "login?faces-redirect=true";
        } else {
            switch (pageId) {
                case LOGIN:
                    return "login?faces-redirect=true";
                case REGISTER:
                    return "register?faces-redirect=true";
                case HOME:
                    return "home?faces-redirect=true";
                case MYPROFILE:
                    return "myprofile?faces-redirect=true";
                case ADDEVENT:
                    return "addEvent?faces-redirect=true";
                case INVITATIONS:
                    return "invitations?faces-redirect=true";
                case SHOWINVITE:
                    return "showInvite?faces-redirect=true";
                case ADMINEVENT:
                    return "adminEvent?faces-redirect=true";
                case EDITEVENT:
                    return "login?faces-redirect=true";
            }
            return "login?faces-redirect=true";
        }
    }

    public static String showEvent(int eventId) {
        return "viewEvent?faces-redirect=trueeventid=" + eventId;
    }
}
