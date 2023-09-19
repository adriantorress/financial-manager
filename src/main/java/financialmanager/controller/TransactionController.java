package financialmanager.controller;

import java.time.LocalDate;
import java.util.List;

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

import financialmanager.model.TransactionModel;
import financialmanager.repository.TransactionRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class TransactionController implements WebMvcConfigurer {

  @Autowired
  TransactionRepository transactionRepository;

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/transaction").setViewName("transactionhistory");

  }

  @GetMapping("/transaction")
  public String showTransaction(Model model, HttpServletRequest request) {

    HttpSession session = request.getSession(false);

    if (session != null && session.getAttribute("username") != null) {

      Integer user_id = (Integer) session.getAttribute("user_id");

      List<TransactionModel> transactions = transactionRepository.findTransactionsByUserId(user_id);

      model.addAttribute("transactions", transactions);
      return "transactionhistory";
    }
    return "redirect:/login";
  }

  @PostMapping("/transaction")
  public String createTransaction(@Valid @ModelAttribute("transaction") TransactionModel transaction,
      BindingResult result,
      RedirectAttributes redirectAttributes,
      HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session != null) {

      if ((String) session.getAttribute("username") != null) {

        transaction.setId_user((Integer) session.getAttribute("user_id"));
        transaction.setData(LocalDate.now());
        transaction.setTipo(request.getParameter("tipo"));
        transaction.setValor(Float.parseFloat(request.getParameter("valor")));
        transaction.setCategoria(request.getParameter("categoria"));
        transaction.setDescricao(request.getParameter("descricao"));
        transactionRepository.save(transaction);

        redirectAttributes.addFlashAttribute("successMessage", "Transação salva com sucesso");
        return "redirect:/wait";
      }
    }
    return "redirect:/";
  }

}