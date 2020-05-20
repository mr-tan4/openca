package com.robert.openca.service.key;

import com.robert.openca.dao.key.KeyPairDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyPairDao extends JpaRepository<KeyPairDO, String> {
}
