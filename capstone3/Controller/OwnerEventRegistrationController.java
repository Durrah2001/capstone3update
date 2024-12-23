package org.example.capstone3.Controller;

import lombok.RequiredArgsConstructor;
import org.example.capstone3.ApiResponse.ApiResponse;
import org.example.capstone3.Service.OwnerEventRegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/owner-event-registration")
@RequiredArgsConstructor
public class OwnerEventRegistrationController {

    private final OwnerEventRegistrationService ownerEventRegistrationService;

    @GetMapping("/get")
    public ResponseEntity getAllEventRegistrations() {
        return ResponseEntity.status(200).body(ownerEventRegistrationService.getAllOwnerEventRegistrations());
    }

    @PostMapping("/owner-registration/{owner_id}/{event_id}/{motorcycle_id}")
    public ResponseEntity ownerRegistration(@PathVariable Integer owner_id, @PathVariable Integer event_id, @PathVariable Integer motorcycle_id) {
        ownerEventRegistrationService.OwnerRegistration(owner_id,event_id,motorcycle_id);
        return ResponseEntity.status(200).body(new ApiResponse("Owner registration"));
    }

}
