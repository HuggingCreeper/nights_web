package ch.jolly.nights.bean;

import ch.jolly.nights.dao.EventDAO;
import ch.jolly.nights.entity.EventEntity;
import ch.jolly.nights.handler.JsonHandler;
import ch.jolly.nights.util.SessionUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;

@ManagedBean
@RequestScoped
public class ViewEventBean implements Serializable {

    private EventEntity eventEntity;

    private String reportKey;

    public ViewEventBean() {
        SessionUtil.getInvited().clear();
        eventEntity = new EventEntity();

        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();

        String eventKey = params.get("eventid");

        try {
            this.eventEntity = EventDAO.getEvent(Integer.valueOf(eventKey));
            try {
                reportKey = JsonHandler.convertGeoCode(String.valueOf(eventEntity.getLatitude()), String.valueOf(eventEntity.getLongitude()));
            } catch (NullPointerException np) {
                System.out.println("Event is empty");
            }
        } catch (Exception e) {
            System.out.println("Keine eventID im Link");
        }
    }

    public EventEntity getEventEntity() {
        return eventEntity;
    }

    public void setEventEntity(EventEntity eventEntity) {
        this.eventEntity = eventEntity;
    }

    public String getReportKey() {
        return reportKey;
    }

    public void setReportKey(String reportKey) {
        this.reportKey = reportKey;
    }
}