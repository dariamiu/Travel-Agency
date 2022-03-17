package model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "destination")
public class Destination {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id_destination;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VacationPackage> vacationPackageList;

    public Destination() {
    }

    public Destination( String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId_destination() {
        return id_destination;
    }

    public void setId_destination(Long id_destination) {
        this.id_destination = id_destination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<VacationPackage> getVacationPackageList() {
        return vacationPackageList;
    }

    public void setVacationPackageList(List<VacationPackage> vacationPackageList) {
        this.vacationPackageList = vacationPackageList;
    }

    public void addVacation(VacationPackage vacationPackage){
        this.vacationPackageList.add(vacationPackage);
    }
}
