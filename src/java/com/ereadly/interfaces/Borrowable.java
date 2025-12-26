package com.ereadly.interfaces;

import com.ereadly.model.Member;

public interface Borrowable {
    void borrow(Member member);
    void returnBook(Member member);
}
