package service;
import model.VacationPackage;
import repository.VacationPackageRepository;

import java.util.List;

public class VacationPackageService {
    private final VacationPackageRepository vacationPackageRepository;

    public VacationPackageService(){
        vacationPackageRepository = new VacationPackageRepository();
    }

    public void createVacation(VacationPackage vacationPackage){
        vacationPackageRepository.insertVacation(vacationPackage);
    }

    public VacationPackage getByName(String name){
        return vacationPackageRepository.findByName(name);
    }

    public void deleteByName(String name){
        vacationPackageRepository.deleteOkCred(name);
    }

    public List<VacationPackage> getAll(){
        return vacationPackageRepository.getAll();
    }

    public void updateAllVacation(String name,VacationPackage vacationPackage){
        vacationPackageRepository.updateAllVacation(name,vacationPackage);
    }

    public void updateTakenPlacesAndStatus(String name, Integer places, String status){
        vacationPackageRepository.updateTakenPlacesAndStatus(name, places, status);
    }
}
