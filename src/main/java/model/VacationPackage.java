package model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "vacation_package")
public class VacationPackage {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id_vacation;

    @Column
    private Float price;

    @Column
    private Integer availablePlaces;

    @Column
    private Integer takenPlaces;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private String status;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destination")
    private Destination destination;

    @ManyToMany(mappedBy = "bookedVacationsList")
    private List<User> users;


    public VacationPackage(){

    }

    public VacationPackage(String name,Float price, Integer availablePlaces, Integer takenPlaces, LocalDate startDate,
                           LocalDate endDate, String status, Destination destination) {
        this.name = name;
        this.price = price;
        this.availablePlaces = availablePlaces;
        this.takenPlaces = takenPlaces;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.destination = destination;
    }


    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(Integer availablePlaces) {
        this.availablePlaces = availablePlaces;
    }

    public Integer getTakenPlaces() {
        return takenPlaces;
    }

    public void setTakenPlaces(Integer takenPlaces) {
        this.takenPlaces = takenPlaces;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
   public List<User> getUsers() {
        return users;
    }


    public void setUsers(List<User> users) {
        this.users = users;
    }

}
