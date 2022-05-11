package com.oneoff.aep.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oneoff.aep.entities.Ponto;

@Repository
public interface IPontoRepository extends JpaRepository<Ponto, Long>{

	@Query("SELECT c FROM Ponto c WHERE c.user.id = :userId")
	List<Ponto> findAllByUserId(@Param("userId") Long userId);
}
