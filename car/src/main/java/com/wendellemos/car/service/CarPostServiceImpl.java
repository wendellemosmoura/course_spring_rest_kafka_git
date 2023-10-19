package com.wendellemos.car.service;

import com.wendellemos.car.dto.CarPostDTO;
import com.wendellemos.car.entity.CarPostEntity;
import com.wendellemos.car.repository.CarPostRepository;
import com.wendellemos.car.repository.OwnerPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarPostServiceImpl implements CarPostService {

    @Autowired
    private CarPostRepository carPostRepository;

    @Autowired
    private OwnerPostRepository ownerPostRepository;

    @Override
    public void newPostDetails(CarPostDTO carPostDTO) {
        CarPostEntity carPostEntity = mapCarDtoToRntity(carPostDTO);
        carPostRepository.save(carPostEntity);
    }

    @Override
    public List<CarPostDTO> getCarSales() {
        List<CarPostDTO> listCarSales = new ArrayList<>();
        carPostRepository.findAll().forEach(item -> listCarSales.add(mapCarEntityToDTO(item)));
        return listCarSales;
    }

    @Override
    public void changeCarSale(CarPostDTO carPostDTO, Long postId) {
        carPostRepository.findById(postId).ifPresentOrElse(item -> {
            item.setDescription(carPostDTO.getDescription());
            item.setContact(carPostDTO.getContact());
            item.setPrice(carPostDTO.getPrice());
            item.setBrand(carPostDTO.getBrand());
            item.setEngineVersion(carPostDTO.getEngineVersion());
            item.setModel(carPostDTO.getModel());

            carPostRepository.save(item);
        }, () -> {
            throw new NoSuchElementException();
        });
    }

    @Override
    public void removeCarSale(Long postId) {
        carPostRepository.deleteById(postId);
    }

    private CarPostDTO mapCarEntityToDTO(CarPostEntity carPostEntity) {
        return CarPostDTO.builder()
                .brand(carPostEntity.getBrand())
                .city(carPostEntity.getCity())
                .model(carPostEntity.getModel())
                .description(carPostEntity.getDescription())
                .engineVersion(carPostEntity.getEngineVersion())
                .createdDate(LocalDateTime.now())
                .ownerName(carPostEntity.getOwnerPost().getName())
                .price(carPostEntity.getPrice()).build();
    }

    private CarPostEntity mapCarDtoToRntity(CarPostDTO carPostDTO) {
        CarPostEntity carPostEntity = new CarPostEntity();

        ownerPostRepository.findById(carPostDTO.getOwnerId()).ifPresentOrElse(item-> {
            carPostEntity.setOwnerPost(item);
            carPostEntity.setContact(item.getContactNumber());
        }, () -> {
            throw new RuntimeException();
        });

        carPostEntity.setModel(carPostDTO.getModel());
        carPostEntity.setBrand(carPostDTO.getBrand());
        carPostEntity.setPrice(carPostDTO.getPrice());
        carPostEntity.setCity(carPostDTO.getCity());
        carPostEntity.setDescription(carPostDTO.getDescription());
        carPostEntity.setEngineVersion(carPostDTO.getEngineVersion());
        carPostEntity.setCreatedDate(carPostDTO.getCreatedDate());

        return carPostEntity;
    }
}
