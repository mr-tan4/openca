package com.robert.openca.service.key;

import com.robert.openca.dao.key.PrivateKeyDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivateKeyDao extends JpaRepository<PrivateKeyDO,String> {
}
