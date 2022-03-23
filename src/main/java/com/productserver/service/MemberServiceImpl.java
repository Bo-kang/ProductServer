package com.productserver.service;

import com.productserver.domain.Member;
import com.productserver.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepo;

    @Override
    public Member registerMember(Member member){
        return memberRepo.save(member);
    }

    @Override
    public Member loginMember(Member member){
        Optional<Member> findMember = memberRepo.findById(member.getUserId());
        if(findMember.isEmpty()) return null;

        if(findMember.get().getUserId() == null) return null;
        if(findMember.get().getUserPassword().compareTo(member.getUserPassword()) != 0) return null;

        return findMember.get();
    }

}
