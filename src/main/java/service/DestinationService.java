package service;

import model.Destination;
import repository.DestinationRepository;

import java.util.List;

public class DestinationService {
    private final DestinationRepository destinationRepository;

    public DestinationService(){
        destinationRepository = new DestinationRepository();
    }

    public void createDestination(Destination destination){
        destinationRepository.insertDestination(destination);
    }

    public Destination getByName(String name){
        return destinationRepository.findByName(name);
    }


    public void deleteDestination(Destination destination){
        destinationRepository.deleteDestination(destination);
    }

    public void updateDescription(String name, String description){
        destinationRepository.updateDetails(name, description);
    }

    public List<Destination> getAll(){
        return destinationRepository.getAll();
    }


}
