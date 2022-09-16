package com.neloarcher.browserplayer.repos;

import com.neloarcher.browserplayer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
