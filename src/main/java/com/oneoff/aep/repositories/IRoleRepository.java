package com.oneoff.aep.repositories;

import com.oneoff.aep.entities.Role;
import com.oneoff.aep.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, String>{
    @Query("SELECT c FROM Role c WHERE c.nomeRole = :id")
    public List<Role> findByRole(@Param("id")String id);

}
