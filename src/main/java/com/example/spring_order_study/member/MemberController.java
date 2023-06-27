package com.example.spring_order_study.member;

import com.example.spring_order_study.item.Item;
import com.example.spring_order_study.order.Customer_Order;
import com.example.spring_order_study.orderdetail.Order_Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

        return "members/memberList";

    }



    @GetMapping("members/{id}/detail")
    public String memberdetail(@PathVariable("id")Long myid, Model model){

        Member member = memberService.find_one(myid);
        model.addAttribute("member",member);
        model.addAttribute("orderCount",member.getCustomerOrders().size());

        int total = 0;
        int count = member.getCustomerOrders().size();
        String items = "";
        for(Customer_Order a : member.getCustomerOrders()){
            for(Order_Item b : a.getOrderItems()){
                total += b.getCount().intValue() * b.getOrderPrice().intValue();

                if(count>1){
                    items += a.getItem().getName()+", ";
                }else{
                    items += a.getItem().getName();
                }

                count--;

            }
        }

        model.addAttribute("orderItems",items);
        model.addAttribute("orderAmount",total);

        return"members/memberDetail";

    }



}
