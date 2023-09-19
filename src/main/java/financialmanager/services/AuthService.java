package financialmanager.services;

import financialmanager.model.UserModel;
import financialmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  @Autowired
  private UserRepository userRepository;

  public boolean authenticateUser(String usuario, String senha) {
    UserModel user = userRepository.findByUsuario(usuario);
    if (user != null && user.getSenha().equals(senha)) {
      return true;
    }
    return false;
  }
}
