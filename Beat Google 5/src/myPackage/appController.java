package myPackage;
import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import code.*;

@Controller
public class appController {
    @GetMapping("/index2")
    public String hello(@ModelAttribute userKeyword userKeyword, Model model) {
    	model.addAttribute("userKeyword", new userKeyword());
//    	model.addAttribute("rArray", new receiver());
        return "index2";
    }
    
    @PostMapping("/search_result")
    public String hello2(@ModelAttribute  userKeyword userKeyword, Model model) throws UnsupportedEncodingException, InterruptedException {
//    	receiver.main(userKeyword.getKeyword());
    	
//    	model.addAttribute("rArray", receiver);
//    	userKeyword.setJsonArray(receiver.main(userKeyword.getKeyword()));
    	receiver receiver = new receiver();
    	model.addAttribute("jsonArray", receiver.main(userKeyword.getKeyword()));
        return "search_result";
    }
}