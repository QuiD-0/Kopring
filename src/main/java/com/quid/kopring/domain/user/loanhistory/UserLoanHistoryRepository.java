package com.quid.kopring.domain.user.loanhistory;

import com.quid.kopring.userLoanHistory.UserLoanHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {

    UserLoanHistory findByBookNameAndIsReturn(String bookName, boolean isReturn);

}
