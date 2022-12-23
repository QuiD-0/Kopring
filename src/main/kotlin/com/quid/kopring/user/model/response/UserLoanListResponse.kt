package com.quid.kopring.user.model.response

import com.quid.kopring.userLoanHistory.UserLoanHistory

data class UserLoanListResponse(val name: String, val loanList: List<UserLoanHistory>) {
}