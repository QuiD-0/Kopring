package com.quid.kopring.domain.user.loanhistory;

import com.quid.kopring.domain.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class JavaUserLoanHistory {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;


  private String bookName;

  private boolean isReturn;

  public JavaUserLoanHistory() {

  }

  public JavaUserLoanHistory(User user, String bookName, boolean isReturn) {
    this.bookName = bookName;
    this.isReturn = isReturn;
  }

  public String getBookName() {
    return this.bookName;
  }

  public void doReturn() {
    this.isReturn = true;
  }

}
