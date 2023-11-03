package com.partshop.controller;

import com.partshop.entity.PartType;
import com.partshop.service.PartTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/parttypes")
public class PartTypeController {

    private PartTypeService partTypeService;

    @Autowired
    public PartTypeController(PartTypeService partTypeService) {
        this.partTypeService = partTypeService;
    }

    @GetMapping
    public List<PartType> getAllPartTypes() {
        return partTypeService.getAllPartTypes();
    }

    @GetMapping("/codes")
    public List<String> getAllPartTypeCodes() {
        //return
        List<PartType> partTypes = partTypeService.getAllPartTypes();

        List<String> partTypeCodes = new ArrayList<>();

        for (PartType partType : partTypes) {
            partTypeCodes.add(partType.getPartTypeCode());
        }
        System.out.println("partTypeCodes " + partTypeCodes);
        return partTypeCodes;
    }

    @GetMapping("/{partTypeCode}")
    public PartType getPartType(@PathVariable String partTypeCode) {
        return partTypeService.getPartType(partTypeCode);
    }

    @PostMapping("/create")
    public PartType createPartType(@RequestBody PartType partType) {
        return partTypeService.save(partType);
    }

}
