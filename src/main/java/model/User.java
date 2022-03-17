package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name  = "user")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id_user;

    @Column
    private String fullName;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String phoneNumber;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_vacation", joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_vacation"))
    private List<VacationPackage> bookedVacationsList;

    public User() {

    }
    public User(String fullName, String email, String password, String phoneNumber) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<VacationPackage> getBookedVacationsList() {
        return bookedVacationsList;
    }

    public void setBookedVacationsList(List<VacationPackage> bookedVacationsList) {
        this.bookedVacationsList = bookedVacationsList;
    }
}


