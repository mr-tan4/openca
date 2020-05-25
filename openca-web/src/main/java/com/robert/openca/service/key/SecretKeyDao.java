package com.robert.openca.service.key;

import com.robert.openca.dao.key.SecretKeyDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretKeyDao extends JpaRepository<SecretKeyDO,String> {
}
