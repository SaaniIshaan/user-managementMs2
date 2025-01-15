package com.tekarch.user_managementMs2.repositories;

import com.tekarch.user_managementMs2.models.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {
    PersonalInfo findByUserUserId(Long userId);
}
