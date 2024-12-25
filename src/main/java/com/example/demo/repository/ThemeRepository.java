package com.example.demo.repository;

import com.example.demo.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ThemeRepository extends JpaRepository<Theme, String> {
    @Modifying
    @Transactional
    @Query(value="DELETE FROM theme WHERE theme_name = :tname", nativeQuery = true)
    void deleteThemeByName(@Param("tname") String themeName);
}
