package financialmanager.repository;

import org.springframework.data.repository.CrudRepository;

import financialmanager.model.BudgetModel;

public interface BudgetRepository extends CrudRepository<BudgetModel, Long> {
}
