package com.partshop.service;

import com.partshop.entity.Part;
import com.partshop.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartService {

    private PartRepository partRepository;

    @Autowired
    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public List<Part> getAllParts() {
        return partRepository.findAll();
    }

    public Part getPart(int partId) {
        return partRepository.findById(partId).orElse(null);
    }

    public Part savePart(Part part) {
        return partRepository.save(part);
    }

    public void deletePart(int partId) {

        partRepository.deleteById(partId);
    }
}
