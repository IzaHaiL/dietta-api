package com.sugadev.culinaryservice.Services;

import com.sugadev.culinaryservice.Dto.CulinaryDTO;
import com.sugadev.culinaryservice.Model.Culinary;
import com.sugadev.culinaryservice.Repository.CulinaryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j

public class CulinaryServiceImpl implements CulinaryService {

    CulinaryRepository culinaryRepository;
    ModelMapper modelMapper;

    @Override
    public CulinaryDTO saveCulinary(CulinaryDTO culinaryDTO) {
        Culinary culinary = modelMapper.map(culinaryDTO, Culinary.class);
        Culinary savedCulinary = culinaryRepository.save(culinary);
        return modelMapper.map(savedCulinary, CulinaryDTO.class);
    }
    @Override
    public CulinaryDTO getCulinary(Integer culinaryId) {
        Culinary culinary = culinaryRepository.findById(culinaryId)
                .orElseThrow(() -> new RuntimeException("Culinary not found with id: " + culinaryId));
        return modelMapper.map(culinary, CulinaryDTO.class);
    }

    @Override
    public List<CulinaryDTO> getAllCulinary() {
        List<Culinary> culinaries = culinaryRepository.findAll();
        return culinaries.stream()
                .map(culinary -> modelMapper.map(culinary, CulinaryDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public CulinaryDTO updateCulinary(int id, CulinaryDTO culinaryDTO) {
        Culinary existingCulinary = culinaryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Detail not found by id : " + id));

        existingCulinary.setIdCulinary(culinaryDTO.getIdCulinary());
        existingCulinary.setFoodname(culinaryDTO.getFoodname());
        existingCulinary.setProtein(culinaryDTO.getProtein());
        existingCulinary.setLemak(culinaryDTO.getLemak());
        existingCulinary.setKarbohidrat(culinaryDTO.getKarbohidrat());
        existingCulinary.setKalori(culinaryDTO.getKalori());
        existingCulinary.setImgurl(culinaryDTO.getImgurl());

        Culinary updatedCulinary = culinaryRepository.save(existingCulinary);
        return modelMapper.map(updatedCulinary, CulinaryDTO.class);
    }

    @Override
    public void deleteCulinary(Integer culinaryId) {culinaryRepository.deleteById(culinaryId);}
}


