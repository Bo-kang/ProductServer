package com.productserver.service;

import com.productserver.domain.Member;

public interface MemberService {
    Member registerMember(Member member);

    Member loginMember(Member member);
}
