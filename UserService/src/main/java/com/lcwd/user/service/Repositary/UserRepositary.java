package com.lcwd.user.service.Repositary;

import com.lcwd.user.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositary  extends JpaRepository<User,String> {
}
