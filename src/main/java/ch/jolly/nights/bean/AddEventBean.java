package ch.jolly.nights.bean;

import ch.jolly.nights.bo.PageIds;
import ch.jolly.nights.dao.EventDAO;
import ch.jolly.nights.dao.UserDAO;
import ch.jolly.nights.entity.EventEntity;
import ch.jolly.nights.entity.UserEntity;
import ch.jolly.nights.handler.PageHandler;
import ch.jolly.nights.util.SessionUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@ManagedBean
@RequestScoped
public class AddEventBean implements Serializable {
    private int profileId;
    private String fullname;

    private List<UserEntity> profiles;

    private EventEntity newEvent;

    private Date fromDate;
    private Date toDate;

    private String name;


    public AddEventBean() {
        profiles = UserDAO.allPeople();
        newEvent = new EventEntity();
    }

    public String submit() {
        newEvent.setFromDate(new Timestamp(fromDate.getTime()));
        newEvent.setToDate(new Timestamp(toDate.getTime()));
        EventDAO.addEvent(newEvent);
        return PageHandler.showPage(PageIds.HOME);

    }

    public List<UserEntity> getProfiles() {
        Iterator<UserEntity> profileIterator = profiles.iterator();
        while (profileIterator.hasNext()) {
            UserEntity oneOfAll = profileIterator.next();
            for (UserEntity invited : SessionUtil.getInvited()) {
                if (oneOfAll.getId() == invited.getId()) {
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
        //ToDo: This could be done way better
        String[] pieces = fullname.split(" ");
        SessionUtil.getInvited().add(new UserEntity(profileId, pieces[0], pieces[1]));
    }

    public List<UserEntity> getInvited() {
        return SessionUtil.getInvited();
    }

    public EventEntity getNewEvent() {
        return newEvent;
    }

    public void setNewEvent(EventEntity newEvent) {
        this.newEvent = newEvent;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}