package financialmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import financialmanager.model.UserModel;
import financialmanager.repository.UserRepository;
import financialmanager.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController implements WebMvcConfigurer {

  @Autowired
  UserRepository userRepository;
  
  @Autowired
  private AuthService authService;

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("login");
    registry.addViewController("/wait").setViewName("wait");
    registry.addViewController("/signup").setViewName("signup");
    registry.addViewController("/").setViewName("financialmanager");
    registry.addViewController("/home").setViewName("financialmanager");
    registry.addViewController("/budget").setViewName("newbudget");
    registry.addViewController("/financial").setViewName("financialreport");
    registry.addViewController("/transaction").setViewName("transactionhistory");
  }

  @GetMapping("/")
  public String showUser(Model model, HttpServletRequest request) {

    HttpSession session = request.getSession(false);
    if (session != null) {
      return "financialmanager";
    }
    return "redirect:/login";
  }

  @GetMapping("/login")
  public String showLogin(Model model, HttpServletRequest request) {

    HttpSession session = request.getSession(false);
    if (session != null) {
      return "redirect:/";
    }

    model.addAttribute("user", new UserModel());
    return "login";
  }

  @GetMapping("/sign-up")
  public String showSignUp(Model model, HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session != null) {
      return "redirect:/";
    }
    model.addAttribute("sign-up", new UserModel());
    return "signup";
  }

  @PostMapping("/login")
  public String login(String usuario, String senha, Model model, HttpServletRequest request) {

    if (authService.authenticateUser(usuario, senha)) {
      UserModel user = userRepository.findByUsuario(usuario);

      HttpSession session = request.getSession(true);
      session.setMaxInactiveInterval(3600);
      session.setAttribute("username", usuario);
      session.setAttribute("user_id", user.getId());

      return "redirect:/";
    } else {
      model.addAttribute("error", "Nome de usuário ou senha incorretos");
      return "login";
    }
  }

   @PostMapping("/logout")
  public String logout(HttpServletRequest request, HttpSession session) {
    session.invalidate();

    SecurityContextHolder.clearContext();
    return "redirect:/login";
  }

  @PostMapping("/sign-up")
  public String getDataForm(@Valid @ModelAttribute("user") UserModel user, BindingResult result,
      RedirectAttributes redirectAttributes, Model model) {

    if (result.hasErrors()) {
      model.addAttribute("error", "Erro ao cadastrar usuário");
      return "signup";
    }

    try {
      userRepository.save(user);
      redirectAttributes.addFlashAttribute("successMessage", "Usuário cadastrado com sucesso");
      return "redirect:/wait";
    } catch (Exception e) {

      model.addAttribute("error", "Erro ao cadastrar o usuário");
      return "signup";
    }
  }

}
