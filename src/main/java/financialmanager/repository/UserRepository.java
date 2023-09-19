package financialmanager.repository;

import org.springframework.data.repository.CrudRepository;

import financialmanager.model.UserModel;

public interface UserRepository extends CrudRepository<UserModel, Long> {
  UserModel findByUsuario(String usuario);
}
