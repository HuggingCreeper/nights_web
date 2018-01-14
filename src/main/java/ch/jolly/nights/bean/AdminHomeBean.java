package ch.jolly.nights.bean;

import ch.jolly.nights.bo.PageIds;
import ch.jolly.nights.bo.Status;
import ch.jolly.nights.dao.EventDAO;
import ch.jolly.nights.entity.EventEntity;
import ch.jolly.nights.handler.PageHandler;
import ch.jolly.nights.util.SessionUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@RequestScoped
public class AdminHomeBean implements Serializable {

    private List<EventEntity> adminList;

    public AdminHomeBean() {
        SessionUtil.getInvited().clear();
        adminList = EventDAO.getEventByStatus(Status.ADMIN);
    }

    public List<EventEntity> getAdminList() {
        return adminList;
    }

    public void setAdminList(List<EventEntity> adminList) {
        this.adminList = adminList;
    }

    public String editEvent(EventEntity p) {
        SessionUtil.setEditEvent(p);
        return PageHandler.showPage(PageIds.EDITEVENT);
    }
}