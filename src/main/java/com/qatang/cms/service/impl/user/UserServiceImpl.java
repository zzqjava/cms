package com.qatang.cms.service.impl.user;

import com.qatang.cms.dao.user.UserDao;
import com.qatang.cms.dao.user.UserRoleDao;
import com.qatang.cms.entity.role.Role;
import com.qatang.cms.entity.user.User;
import com.qatang.cms.form.user.UserForm;
import com.qatang.cms.service.user.UserService;
import com.qatang.cms.utils.Digests;
import com.qatang.cms.utils.Encodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by qatang on 14-6-12.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    private static final int SALT_SIZE = 8;

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public User get(Long id) {
        return userDao.findOne(id);
    }

    @Override
    public User save(User user) {
        entryptPassword(user);
        return userDao.save(user);
    }

    @Override
    public User update(User user) {
        return userDao.save(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public User getByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User getByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public Page<User> getAll(UserForm userForm) {
        return userDao.findAll(userForm);
    }

    @Override
    public List<Role> getByUserId(Long userId) {
        return userRoleDao.findByUserId(userId);
    }

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    private void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }
}
