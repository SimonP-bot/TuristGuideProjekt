package com.example.turistguideprojekt.controller;

import com.example.turistguideprojekt.model.TouristAttraction;
import com.example.turistguideprojekt.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attractions")
@CrossOrigin(origins = "*")
public class TouristController {
    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping("")
    public ResponseEntity<List<TouristAttraction>> getAllAttraction() {
        List<TouristAttraction> touristAttractions = touristService.getAllAttractions();
        return new ResponseEntity<>(touristAttractions, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<TouristAttraction> getAttractionByName(@PathVariable String name) {
        TouristAttraction touristAttraction = touristService.getAttrationByName(name);
        return new ResponseEntity<>(touristAttraction,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<TouristAttraction> addAttraction(@RequestBody TouristAttraction touristAttraction) {
        TouristAttraction myAttraction = touristService.addAttraction(touristAttraction);
        return new ResponseEntity<>(myAttraction,HttpStatus.CREATED);
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<TouristAttraction> updateAttraction(@PathVariable String name, @RequestBody TouristAttraction updatedAttraction) {
        TouristAttraction attraction = touristService.updateAttraction(name, updatedAttraction);
        if (attraction != null) {
            return new ResponseEntity<>(attraction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteAttraction(@PathVariable String name) {
        boolean deleted = touristService.deleteAttraction(name);

        if (deleted) {
            return new ResponseEntity<>("Attraktionen: " + name + "' blev slettet.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Attraktionen: " + name + "' blev ikke fundet.", HttpStatus.NOT_FOUND);
        }
    }

}
