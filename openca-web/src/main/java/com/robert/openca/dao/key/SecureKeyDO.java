package com.robert.openca.dao.key;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 对称密钥实体类
 *
 * @author robert
 * @since 2020-05-20
 * @version 1.0
 */
@Entity
@Table(name = "SECURE_KEY_INFO")
public class SecureKeyDO extends KeyDO {
}
