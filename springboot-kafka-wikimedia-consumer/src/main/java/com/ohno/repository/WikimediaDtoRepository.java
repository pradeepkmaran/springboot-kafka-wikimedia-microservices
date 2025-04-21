package com.ohno.repository;

import com.ohno.dto.WikimediaDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaDtoRepository extends JpaRepository<WikimediaDto, Long> {
}
