package financialmanager.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import financialmanager.model.UserModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class FinancialController {

  @GetMapping("/financial")
  public String showFinancial(Model model, HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    model.addAttribute("financial", new UserModel());
    if (session != null) {
      return "financialreport";
    }
    return "redirect:/login";
  }
  
}
