package ch.jolly.nights.entity;

import javax.persistence.*;

@Entity
@Table(name = "placeofresidence", schema = "nights", catalog = "")
public class PlaceOfResidenceEntity {
    private int id;
    private String place;
    private String plz;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "place")
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Basic
    @Column(name = "plz")
    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaceOfResidenceEntity that = (PlaceOfResidenceEntity) o;

        if (id != that.id) return false;
        if (place != null ? !place.equals(that.place) : that.place != null) return false;
        if (plz != null ? !plz.equals(that.plz) : that.plz != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (plz != null ? plz.hashCode() : 0);
        return result;
    }
}
