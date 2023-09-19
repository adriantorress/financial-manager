package financialmanager.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import financialmanager.model.BudgetModel;
import financialmanager.model.UserModel;
import financialmanager.repository.BudgetRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class BudgetController implements WebMvcConfigurer {

  @Autowired
  BudgetRepository budgetRepository;

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/budget").setViewName("newbudget");
  }

  @GetMapping("/budget")
  public String showBudget(Model model, HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    model.addAttribute("budget", new UserModel());
    if (session != null) {
      return "newbudget";
    }
    return "redirect:/login";
  }

  @PostMapping("/budget")
  public String createBudget(@Valid @ModelAttribute("budget") BudgetModel budget,
      BindingResult result,
      RedirectAttributes redirectAttributes,
      HttpServletRequest request) {
        
    HttpSession session = request.getSession(false);

    if (session != null) {

      if ((String) session.getAttribute("username") != null) {

        budget.setUsuario((String) session.getAttribute("username"));
        budget.setId_user((Integer) session.getAttribute("user_id"));
        budget.setMesAno(LocalDate.now());
        budget.setValorOrcado(Float.parseFloat(request.getParameter("valorOrcado")));
        budget.setCategoriagasto(request.getParameter("categoriagasto"));
        budget.setDescricao(request.getParameter("descricao"));
        budgetRepository.save(budget);

        redirectAttributes.addFlashAttribute("successMessage", "Orçamento salvo com sucesso");
        return "redirect:/wait";
      }
    }
    return "redirect:/";
  }

}
