package com.example.spring_order_study.member;

import com.example.spring_order_study.order.Customer_Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MemberController {

    @Autowired MemberService memberService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("members/new")
    public String member_new(Model model){
        // createMemberForm에서 memberForm이라는 Dto 객체를 필요로 하므로, dto객체를 만들어서 model을 통해 전달
        // DTO에서 NotNull, NotEmpty등 validation 처리를 할 수가 있고, 해당 객체를 createMemberForm화면에 전달함으로서
        // 화면에서 validation체크를 할 수가 있다.
        model.addAttribute("memberForm",new MemberDto());
        return "members/createMemberForm";
    }


    @PostMapping("members/new")
    public String member_new_post(MemberDto member)
    {
        memberService.member_save(member);
        return "redirect:/members";

    }

    @GetMapping("members")
    public String memberFindAll(Model model) throws SQLException {   //타서버 조회시 강제시킴

        List<Member> members = memberService.member_findall() ;
        model.addAttribute("members",members);

        // 주문수량 주문금액 합계
        for(Member a : members){
            System.out.println(a.getName()+"의 주문 갯수는 : "+a.getCustomerOrders().size());
            int order_total = 0;
            for(Customer_Order b : a.getCustomerOrders()){
             //   System.out.println(b.getItem().getPrice().intValue() +"*"+b.getCount().intValue());
                order_total += b.getItem().getPrice().intValue() * b.getCount().intValue();
            }
            System.out.println("주문 금액은 : "+order_total);
        }


        return "members/memberList";

    }



}
