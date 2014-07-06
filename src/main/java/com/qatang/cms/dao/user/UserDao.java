package com.qatang.cms.dao.user;

import com.qatang.cms.entity.user.User;
import com.qatang.cms.form.user.UserForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by qatang on 14-6-12.
 */
public interface UserDao extends JpaRepository<User, Long> {

    public User findByUsername(String username);

    public User save(User user);

    public List<User> findByCondition(UserForm userForm);
}
