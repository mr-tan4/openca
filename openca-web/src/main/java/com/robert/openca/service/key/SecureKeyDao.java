package com.robert.openca.service.key;

import com.robert.openca.dao.key.SecureKeyDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecureKeyDao extends JpaRepository<SecureKeyDO,String> {
}
