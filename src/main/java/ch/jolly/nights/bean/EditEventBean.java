package ch.jolly.nights.bean;

import ch.jolly.nights.CommonMethod;
import ch.jolly.nights.bo.PageIds;
import ch.jolly.nights.dao.EventDAO;
import ch.jolly.nights.dao.UserDAO;
import ch.jolly.nights.entity.EventEntity;
import ch.jolly.nights.entity.ParticipantEntity;
import ch.jolly.nights.entity.UserEntity;
import ch.jolly.nights.handler.PageHandler;
import ch.jolly.nights.util.SessionUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@ManagedBean
@RequestScoped
public class EditEventBean implements Serializable {
    private EventEntity eventEntity;

    private String pageDate;
    private String pageTime;

    private int profileId;
    private String fullname;

    private List<UserEntity> profiles;

    public EditEventBean() {
        profiles = UserDAO.allPeople();
        eventEntity = SessionUtil.getEditEvent();

        pageDate = CommonMethod.timestampToStringDate(eventEntity.getToDate());
        pageTime = CommonMethod.timestampToStringTime(eventEntity.getToDate());
    }

    public String submit() {
        eventEntity.setToDate(CommonMethod.convertTimestamp(pageDate, pageTime));
        EventDAO.editEvent(eventEntity);
        SessionUtil.setEditEvent(null);
        return PageHandler.showEvent(eventEntity.getId());
    }

    public String deleteEvent() {
        EventDAO.deleteEvent(eventEntity);
        return PageHandler.showPage(PageIds.ADMINEVENT);
    }

    public List<UserEntity> getProfiles() {
        Iterator<UserEntity> profileIterator = profiles.iterator();
        while (profileIterator.hasNext()) {
            UserEntity oneOfAll = profileIterator.next();
            for (ParticipantEntity invited : eventEntity.getInvited()) {
                if (oneOfAll.getId() == invited.getId()) {
                    profileIterator.remove();
                }
            }
            for (ParticipantEntity in : eventEntity.getInvited()) {
                if (oneOfAll.getId() == in.getUser().getId()) {
                    profileIterator.remove();
                }
            }
        }

        return profiles;
    }

    public void setProfiles(List<UserEntity> profiles) {
        this.profiles = profiles;
    }

    public void doAThing() {
        String[] pieces = fullname.split(" ");
        SessionUtil.getInvited().add(new UserEntity(profileId, pieces[0], pieces[1]));
    }

    public Collection<UserEntity> getInvited() {
        return SessionUtil.getInvited();
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPageDate() {
        return pageDate;
    }

    public void setPageDate(String pageDate) {
        this.pageDate = pageDate;
    }

    public String getPageTime() {
        return pageTime;
    }

    public void setPageTime(String pageTime) {
        this.pageTime = pageTime;
    }

    public EventEntity getEventEntity() {
        return eventEntity;
    }

    public void setEventEntity(EventEntity eventEntity) {
        this.eventEntity = eventEntity;
    }
}