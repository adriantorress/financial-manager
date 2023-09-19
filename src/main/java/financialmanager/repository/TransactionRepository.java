package financialmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import financialmanager.model.TransactionModel;

public interface TransactionRepository extends CrudRepository<TransactionModel, Long> {
  @Query("SELECT t FROM TransactionModel t WHERE t.id_user = :id_user")
  List<TransactionModel> findTransactionsByUserId(@Param("id_user") Integer id_user);
}
