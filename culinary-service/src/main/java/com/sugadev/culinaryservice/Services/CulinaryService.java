package com.sugadev.culinaryservice.Services;

import com.sugadev.culinaryservice.Dto.CulinaryDTO;
import com.sugadev.culinaryservice.Model.Culinary;

import java.util.List;

public interface CulinaryService {
    CulinaryDTO saveCulinary (CulinaryDTO culinaryDTO);

    CulinaryDTO getCulinary(Integer culinaryId);

    List<CulinaryDTO> getAllCulinary();

    CulinaryDTO updateCulinary(int id, CulinaryDTO culinary);

    void deleteCulinary(Integer culinaryId);
}
