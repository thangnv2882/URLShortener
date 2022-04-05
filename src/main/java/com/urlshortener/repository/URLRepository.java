package com.urlshortener.repository;

import com.urlshortener.model.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface URLRepository extends JpaRepository<URL, Long> {
    URL findByURLId(Long id);

}
