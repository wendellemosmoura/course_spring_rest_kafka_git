package com.wendellemos.api.controller;

import com.wendellemos.api.dto.CarPostDTO;
import com.wendellemos.api.message.KafkaProducerMessage;
import com.wendellemos.api.service.CarPostStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarPostController {

    private final Logger LOG = LoggerFactory.getLogger(CarPostController.class);

    @Autowired
    private CarPostStoreService carPostStoreService;

    @Autowired
    private KafkaProducerMessage kafkaProducerMessage;

    @PostMapping("/post")
    public ResponseEntity postCarForSale(@RequestBody CarPostDTO carPostDTO) {
        LOG.info("Produced Car Post information: {}", carPostDTO);
        kafkaProducerMessage.sendMessage(carPostDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<CarPostDTO>> getCarSales() {
        return ResponseEntity.status(HttpStatus.FOUND).body(carPostStoreService.getCarForSales());
    }

    @PutMapping("/{id}")
    public ResponseEntity changeCarSale(@RequestBody CarPostDTO carPostDTO, @PathVariable("id") String id) {
        carPostStoreService.changeCarForSale(carPostDTO, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCarForSale(@PathVariable("id") String id){
        carPostStoreService.removeCarForSale(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
