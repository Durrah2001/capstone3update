package org.example.capstone3.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone3.ApiResponse.ApiException;
import org.example.capstone3.Model.Company;
import org.example.capstone3.Model.Motorcycle;
import org.example.capstone3.Model.Owner;
import org.example.capstone3.OutDTO.MotorcycleOutDTO;
import org.example.capstone3.Repository.CompanyRepository;
import org.example.capstone3.Repository.MotorcycleRepository;
import org.example.capstone3.Repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MotorcycleService {

    private final MotorcycleRepository motorcycleRepository;
    private final OwnerRepository ownerRepository;
    private final CompanyRepository companyRepository;

    public List<MotorcycleOutDTO> getAllMotorcycles(){

        List<Motorcycle> motorcycles = motorcycleRepository.findAll();

        List<MotorcycleOutDTO> motorcycleDTOS = new ArrayList<>();

        for(Motorcycle motorcycle : motorcycles){

            MotorcycleOutDTO motorcycleDTO = new MotorcycleOutDTO(motorcycle.getBrand(), motorcycle.getModel(), motorcycle.getYear(), motorcycle.getPrice(), motorcycle.getColor(), motorcycle.getIsAvailable(), motorcycle.getIsForSale());

            motorcycleDTOS.add(motorcycleDTO);
        }

        return motorcycleDTOS;
    }

    public void addMotorcycleByOwner(Integer owner_id, Motorcycle motorcycle) {

        Owner owner = ownerRepository.findOwnerById(owner_id);

        if (owner == null)
            throw new ApiException("Owner not found!");

        //assign motorcycle to one owner
        motorcycle.setOwner(owner);
        motorcycleRepository.save(motorcycle);
    }

    public void addMotorcycleByCompany(Integer company_id, Motorcycle motorcycle) {

        Company company = companyRepository.findCompanyById(company_id);

        if (company == null)
            throw new ApiException("Company not found!");

        //assign motorcycle to one company
        motorcycle.setCompany(company);
        motorcycleRepository.save(motorcycle);
    }


    public void updateMotorcycle(Integer id, Motorcycle motorcycle) {

        Motorcycle m = motorcycleRepository.findMotorcycleById(id);
        if (m == null)
            throw new ApiException("Motorcycle not found!");

        m.setBrand(motorcycle.getBrand());
        m.setModel(motorcycle.getModel());
        m.setPrice(motorcycle.getPrice());
        m.setColor(motorcycle.getColor());
        m.setYear(motorcycle.getYear());
        m.setIsAvailable(motorcycle.getIsAvailable());
        m.setIsForSale(motorcycle.getIsForSale());
        motorcycleRepository.save(m);
    }

    public void deleteMotorcycle(Integer id){

        Motorcycle motorcycle = motorcycleRepository.findMotorcycleById(id);
        if(motorcycle == null)
            throw new ApiException("Motorcycle not found!");

        motorcycleRepository.delete(motorcycle);

    }


    ////


    //Durrah
    public List<Motorcycle> filterByPrice(Double minPrice, Double maxPrice) {
        if (minPrice == null && maxPrice == null) {
            return motorcycleRepository.findAll(); // No filter, return all motorcycles
        }
        if (minPrice != null && maxPrice != null) {
            return motorcycleRepository.findByPriceBetween(minPrice, maxPrice);
        }
        if (minPrice != null) {
            return motorcycleRepository.findByPriceGreaterThanEqual(minPrice);
        }
        if (maxPrice != null) {
            return motorcycleRepository.findByPriceLessThanEqual(maxPrice);
        }
        return motorcycleRepository.findAll();
    }







}
