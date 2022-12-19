package com.quid.kopring.service.book;

import com.quid.kopring.book.Book;
import com.quid.kopring.book.repository.BookJpaRepository;
import com.quid.kopring.dto.book.request.BookLoanRequest;
import com.quid.kopring.dto.book.request.BookRequest;
import com.quid.kopring.dto.book.request.BookReturnRequest;
import com.quid.kopring.user.User;
import com.quid.kopring.user.repository.UserJpaRepository;
import com.quid.kopring.userLoanHistory.repository.UserLoanHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookJpaRepository bookRepository;
    private final UserJpaRepository userRepository;
    private final UserLoanHistoryJpaRepository userLoanHistoryRepository;

    @Transactional
    public void saveBook(BookRequest request) {
        Book newBook = new Book(null, request.getName());
        bookRepository.save(newBook);
    }

    @Transactional
    public void loanBook(BookLoanRequest request) {
        Book book = bookRepository.findByName(request.getBookName())
            .orElseThrow(IllegalArgumentException::new);
        if (userLoanHistoryRepository.findByBookNameAndIsReturn(request.getBookName(), false)
            != null) {
            throw new IllegalArgumentException("진작 대출되어 있는 책입니다");
        }

        User user = userRepository.findByName(request.getUserName())
            .orElseThrow(IllegalArgumentException::new);
        user.loanBook(book);
    }

    @Transactional
    public void returnBook(BookReturnRequest request) {
        User user = userRepository.findByName(request.getUserName())
            .orElseThrow(IllegalArgumentException::new);
        user.returnBook(request.getBookName());
    }

}
