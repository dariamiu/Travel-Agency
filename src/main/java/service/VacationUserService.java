package service;

import repository.VacationUserRepository;

public class VacationUserService {

    private final VacationUserRepository vacationUserRepository;

    public VacationUserService() {
        this.vacationUserRepository = new VacationUserRepository();
    }

    public void bookVacation(String userEmail, String vacationName){
        vacationUserRepository.bookVacation(userEmail,vacationName);
    }
}
