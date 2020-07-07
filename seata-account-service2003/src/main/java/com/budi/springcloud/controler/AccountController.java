package com.budi.springcloud.controler;

import com.budi.springcloud.domain.CommResult;
import com.budi.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
public class AccountController {
    @Resource
    private AccountService accountService;
    @RequestMapping("/account/decrease")
    public CommResult decrease(@RequestParam("userId")Long userId, @RequestParam("money")BigDecimal money)
    {
       accountService.decrease(userId,money);
       return new CommResult(200,"账户扣减余额成功");
    }
}
