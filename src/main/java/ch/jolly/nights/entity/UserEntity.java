package ch.jolly.nights.entity;

import ch.jolly.nights.CommonMethod;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "user", schema = "nights")
@NamedQueries({
        @NamedQuery(query = "Select e from UserEntity e where e.id = :id",
                name = "find User by id"),
        @NamedQuery(query = "Select e from UserEntity e where e.username = :username and e.password = :password",
                name = "getLoginUser")
})
public class UserEntity {
    private int id;
    private String email;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private Date birthday;
    private AddressEntity address;
    private PlaceOfResidenceEntity place;

    public UserEntity(String email, String username, String password, String firstname, String lastname, Date birthday) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
    }

    public UserEntity(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public UserEntity() {
    }

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @OneToOne
    @JoinColumn(name = "address")
    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    @OneToOne
    @JoinColumn(name = "place")
    public PlaceOfResidenceEntity getPlace() {
        return place;
    }

    public void setPlace(PlaceOfResidenceEntity place) {
        this.place = place;
    }

    @Transient
    public String getFormatedDate() {
        return CommonMethod.dateToString(this.birthday);
    }
}
