package com.gpt5.gpt5.Controller;

import com.gpt5.gpt5.Service.OllamaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChatController {

    private final OllamaService ollamaService;

    public ChatController(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }

    @GetMapping("/Chatpage")
    public String chatPage(){

        return "ChatPage";
    }



   @ResponseBody
   @PostMapping("/chat")
   public ResponseEntity<String> chat(@RequestParam("message") String message) {

       String response = ollamaService.response(message);

       return ResponseEntity.ok(response);
   }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
