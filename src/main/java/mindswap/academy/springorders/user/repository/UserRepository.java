package mindswap.academy.springorders.user.repository;

import mindswap.academy.springorders.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
