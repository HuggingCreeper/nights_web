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
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class PendingBean implements Serializable {

    private List<EventEntity> invitations;

    public PendingBean() {
        invitations = new ArrayList<>();
        invitations = EventDAO.getEventByStatus(Status.PENDING);
    }

    public List<EventEntity> getInvitations() {
        return invitations;
    }

    public String showInvite(EventEntity p) {
        SessionUtil.setEditEvent(p);
        return PageHandler.showPage(PageIds.SHOWINVITE);
    }
}