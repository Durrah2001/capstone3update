package org.example.capstone3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone3.ApiResponse.ApiResponse;
import org.example.capstone3.Model.Motorcycle;
import org.example.capstone3.OutDTO.MotorcycleOutDTO;
import org.example.capstone3.Service.MotorcycleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/motorcycle-system/motorcycle")
@RequiredArgsConstructor
public class MotorcycleController {

    private final MotorcycleService motorcycleService;

    @GetMapping("/get")
    public ResponseEntity getAllMotorcycles(){
        List<MotorcycleOutDTO> motorcycleDTOS = motorcycleService.getAllMotorcycles();
        return ResponseEntity.status(200).body(motorcycleDTOS);
    }


    @PostMapping("/add-by-owner/{owner_id}")
    public ResponseEntity addMotorcycleByOwner(@PathVariable Integer owner_id, @RequestBody @Valid Motorcycle motorcycle) {

        motorcycleService.addMotorcycleByOwner(owner_id, motorcycle);
        return ResponseEntity.status(200).body(new ApiResponse("Motorcycle added successfully!"));
    }

    @PostMapping("/add-by-company/{company_id}")
    public ResponseEntity addMotorcycleByCompany(@PathVariable Integer company_id, @RequestBody @Valid Motorcycle motorcycle) {

        motorcycleService.addMotorcycleByCompany(company_id, motorcycle);
        return ResponseEntity.status(200).body(new ApiResponse("Motorcycle added successfully!"));
    }



    @PutMapping("/update/{id}")
    public ResponseEntity updateMotorcycle(@PathVariable Integer id, @RequestBody @Valid Motorcycle motorcycle){
        motorcycleService.updateMotorcycle(id, motorcycle);
        return ResponseEntity.status(200).body(new ApiResponse("Motorcycle updated successfully!"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMotorcycle(@PathVariable Integer id){

        motorcycleService.deleteMotorcycle(id);
        return ResponseEntity.status(200).body(new ApiResponse("Motorcycle deleted successfully!"));

    }


    @GetMapping("/filter-price/{minPrice}/{maxPrice}")
    public ResponseEntity filterMotorcyclesByPrice(
            @PathVariable Double minPrice,
            @PathVariable Double maxPrice) {

        List<Motorcycle> filteredMotorcycles = motorcycleService.filterByPrice(minPrice, maxPrice);
        return ResponseEntity.ok(filteredMotorcycles);
    }









}
