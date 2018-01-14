package ch.jolly.nights.entity;

import javax.persistence.*;

@Entity
@Table(name = "participant", schema = "nights")
public class ParticipantEntity {
    private int id;
    private EventEntity event;
    private UserEntity user;
    private StatusEntity status;

    public ParticipantEntity() {
    }

    public ParticipantEntity(EventEntity event, UserEntity user, StatusEntity status) {
        this.event = event;
        this.user = user;
        this.status = status;
    }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "event")
    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    @OneToOne
    @JoinColumn(name = "user")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }


    @OneToOne
    @JoinColumn(name = "status")
    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParticipantEntity that = (ParticipantEntity) o;

        if (id != that.id) return false;
        if (event != that.event) return false;
        if (user != that.user) return false;
        return status == that.status;
    }

}
