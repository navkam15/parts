package com.partshop.controller;

import com.partshop.entity.Part;
import com.partshop.entity.PartModel;
import com.partshop.entity.PartType;
import com.partshop.entity.User;
import com.partshop.service.PartService;
import com.partshop.service.PartTypeService;
import com.partshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/parts")
public class PartController {

    @Autowired
    private PartService partService;

    @Autowired
    private UserService userService;

    @Autowired
    private PartTypeService partTypeService;

    @GetMapping
    public List<Part> getAllParts() {
        return partService.getAllParts();
    }

    @GetMapping("/{partId}")
    public Part getPart(@PathVariable int partId) {
        return partService.getPart(partId);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createPart(@RequestBody PartModel partModel) {
        System.out.println("partModel " + partModel);
        Map<String, Object> hmap = new HashMap<String, Object>();
        Part partFromDb = partService.getPart(partModel.getPartId());
        if (partFromDb != null) {
            hmap.put("response", "Part Id already exists");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }


        PartType partTypeFromDb = partTypeService.getPartType(partModel.getPartTypeCode());
        if (partTypeFromDb == null) {
            hmap.put("response", "Invalid Part Type Code");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUser = userService.getLoggedInUser();

        User loggedIn = loggedInUser != null ? userService.getUser(loggedInUser) : null;
        Part part = new Part();

        part.setPartId(partModel.getPartId());
        System.out.println("partid : " + part.getPartId());
        part.setPartDesc(partModel.getPartDesc());
        part.setPartName(partModel.getPartName());
        part.setQuantity(partModel.getQuantity());
        if (part.getQuantity() < 0) {
            part.setQuantity(0);
        }

        part.setPartType(partTypeFromDb);

        part.setCreatedBy(loggedIn);
        Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
        part.setCreatedDate(timeStamp);

        Part partFromDb2 = partService.savePart(part);

        hmap.put("part", partFromDb2);
        hmap.put("createdBy", loggedIn);

        return new ResponseEntity<>(hmap, HttpStatus.CREATED);


    }


    @PutMapping(value = "/update")
    public ResponseEntity<Map<String, Object>> updatePart(@RequestBody Part part) {

        Part partFromDb = partService.getPart(part.getPartId());
        if (partFromDb == null) {
            return null;
        }

        String loggedInUser = userService.getLoggedInUser();

        User loggedIn = loggedInUser != null ? userService.getUser(loggedInUser) : null;
        part.setUpdatedBy(loggedIn);
        Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
        part.setUpdatedDate(timeStamp);

        part.setCreatedBy(partFromDb.getCreatedBy());
        part.setCreatedDate(partFromDb.getCreatedDate());
        if (part.getQuantity() < 0) {
            part.setQuantity(0);
        }

        Part partFromDb2 = partService.savePart(part);
        Map<String, Object> hmap = new HashMap<String, Object>();
        hmap.put("part", partFromDb2);
        hmap.put("updatedBy", loggedIn);

        return new ResponseEntity<>(hmap, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{partId}")
    public ResponseEntity<Map<String, Object>> deletePart(@PathVariable int partId) {
        Map<String, Object> hmap = new HashMap<String, Object>();
        Part fromDb = partService.getPart(partId);
        if (fromDb == null) {

            hmap.put("response", "Invalid part");
            return new ResponseEntity<>(hmap, HttpStatus.BAD_REQUEST);
        }

        partService.deletePart(partId);
        hmap.put("response", fromDb.getPartName() + " part is deleted");
        return new ResponseEntity<>(hmap, HttpStatus.OK);
    }


}
