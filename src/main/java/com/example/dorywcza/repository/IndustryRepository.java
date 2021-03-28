package com.example.dorywcza.repository;

import com.example.dorywcza.model.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IndustryRepository extends JpaRepository<Industry, Long> {

}
