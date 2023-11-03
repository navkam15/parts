package com.partshop.service;

import com.partshop.entity.PartType;
import com.partshop.repository.PartTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartTypeService {

    private PartTypeRepository partTypeRepository;

    @Autowired
    public PartTypeService(PartTypeRepository partTypeRepository) {
        this.partTypeRepository = partTypeRepository;
    }

    public List<PartType> getAllPartTypes() {
        return partTypeRepository.findAll();
    }

    public PartType getPartType(String partTypeCode) {
        return partTypeRepository.findById(partTypeCode).orElse(null);
    }

    public PartType save(PartType partType) {
        return partTypeRepository.save(partType);
    }
}
