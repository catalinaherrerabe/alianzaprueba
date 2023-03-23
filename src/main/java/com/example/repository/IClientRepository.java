package com.example.repository;

import com.example.entities.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.Optional;

@Repository
public interface IClientRepository extends JpaRepository<ClientEntity, Integer> {

    Optional<ClientEntity> findBySharedKey(String sharedKey);

    @Override
    Page<ClientEntity> findAll(Pageable pageable);

    @Query("SELECT c FROM ClientEntity c "
            + " WHERE (:sharedKey is null or c.sharedKey = :sharedKey) "
            + " AND (:businessId is null or c.businessId = :businessId) "
            + " AND (:email is null or c.email = :email) "
            + " AND (:phone is null or c.phone = :phone) "
            + " AND (:dateAdd is null or c.dateAdd = :dateAdd) "    )
    Page<ClientEntity> findByFilter(@Param("sharedKey") String sharedKey, @Param("businessId") String businessId,
                                    @Param("email") String email, @Param("phone") String phone,
                                    @Param("dateAdd") Date dateAdd );

}
