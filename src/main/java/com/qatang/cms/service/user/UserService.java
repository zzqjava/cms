package com.qatang.cms.service.user;

import com.qatang.cms.entity.role.Role;
import com.qatang.cms.entity.user.User;
import com.qatang.cms.form.user.UserForm;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by qatang on 14-6-12.
 */
public interface UserService {
    public User getByUsername(String username);

    public User getByEmail(String email);

    public User get(Long id);

    public User save(User user);

    public User update(User user);

    public void delete(Long id);

    public Page<User> getAll(UserForm userForm);

    public List<Role> getByUserId(Long userId);
}
