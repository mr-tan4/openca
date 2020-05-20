package com.robert.openca.service.key;

import com.robert.openca.dao.key.PublicKeyDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicKeyDao extends JpaRepository<PublicKeyDO,String> {
}
