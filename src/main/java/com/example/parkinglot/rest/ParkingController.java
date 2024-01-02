package com.example.parkinglot.rest;

import com.example.parkinglot.entity.Ticket;
import com.example.parkinglot.request.CreateTicketReq;
import com.example.parkinglot.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ParkingController {

    private final TicketService ticketService;

    @PostMapping("/getTicket")
    public ResponseEntity<Ticket> getTicket(@RequestBody CreateTicketReq createTicketReq) throws Exception {
        return ResponseEntity.ok(ticketService.generateTicket(createTicketReq));
    }

    @DeleteMapping("/deleteTicket/{slot}/{vehicleType}")
    public ResponseEntity<Integer> deleteTicket(@PathVariable(name = "slot") int slot, @PathVariable(name = "vehicleType") String vehicleType){
        return ResponseEntity.ok(ticketService.onExit(slot, vehicleType));
    }

    @GetMapping("/getPass/{vehicleNo}/{days}/{vehicleType}")
    public ResponseEntity<Integer> getPass(@PathVariable(name = "vehicleNo") int vehicleNo, @PathVariable(name = "days") int days,@PathVariable(name = "vehicleType") String vehicleType){
        return ResponseEntity.ok(ticketService.addPass(vehicleNo, days, vehicleType));
    }
}
