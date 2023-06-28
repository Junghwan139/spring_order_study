package com.example.spring_order_study.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MemberService {

    @Autowired MemberRepository memberRepository;


    // Create
    public void member_save(MemberDto member){
        Address address = new Address(member.getCity(), member.getStreet(),member.getZipcode());
        Member member1 = Member.builder()
                .name(member.getName())
                .email(member.getEmail())
                .password(member.getPassword())
                .address(address)
                .build();

        memberRepository.save(member1);

    }

    // Read_all

    public List<Member> member_findall(){
        return memberRepository.findAll();
    }

    // Read_one
    public Member find_one(Long id){
        return memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }



}
