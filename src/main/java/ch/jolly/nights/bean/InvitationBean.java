package ch.jolly.nights.bean;

import ch.jolly.nights.CommonMethod;
import ch.jolly.nights.bo.PageIds;
import ch.jolly.nights.bo.Status;
import ch.jolly.nights.dao.EventDAO;
import ch.jolly.nights.entity.EventEntity;
import ch.jolly.nights.handler.PageHandler;
import ch.jolly.nights.util.SessionUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class InvitationBean implements Serializable {

    private EventEntity eventEntity;

    public InvitationBean() {
        eventEntity = SessionUtil.getEditEvent();
    }

    public String getPageDate() {
        return CommonMethod.timestampToStringDate(eventEntity.getToDate());
    }


    public String getPageTime() {
        return CommonMethod.timestampToStringTime(eventEntity.getToDate());
    }

    public String accept() {
        EventDAO.editEventStatus(eventEntity, Status.ACCEPTED);
        return PageHandler.showPage(PageIds.INVITATIONS);
    }

    public String decline() {
        EventDAO.editEventStatus(eventEntity, Status.DECLINED);
        return PageHandler.showPage(PageIds.INVITATIONS);
    }

    public EventEntity getEventEntity() {
        return eventEntity;
    }

    public void setEventEntity(EventEntity eventEntity) {
        this.eventEntity = eventEntity;
    }
}