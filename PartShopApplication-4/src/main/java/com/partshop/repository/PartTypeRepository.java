package com.partshop.repository;

import com.partshop.entity.PartType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartTypeRepository extends JpaRepository<PartType, String> {
}
