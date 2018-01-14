package ch.jolly.nights.bean;

import ch.jolly.nights.bo.Status;
import ch.jolly.nights.dao.EventDAO;
import ch.jolly.nights.entity.EventEntity;
import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class HomeBean implements Serializable {

    private Logger logger = Logger.getLogger(HomeBean.class);

    private List<EventEntity> accepted;

    private List<EventEntity> hosting;

    public HomeBean() {
        logger.info("Loading HomeBean");
        accepted = new ArrayList<>();
        accepted = EventDAO.getEventByStatus(Status.ACCEPTED);
        hosting = new ArrayList<>();
        hosting = EventDAO.getEventByStatus(Status.ADMIN);
    }

    public List<EventEntity> getAccepted() {
        return accepted;
    }

    public List<EventEntity> getHosting() {
        return hosting;
    }
}