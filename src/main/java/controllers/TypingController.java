package controllers;

import dao.TypingDAO;
import entities.Typing;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Friday on 06/05/2018.
 */
@RestController
public class TypingController {

    private TypingDAO typingDAO= new TypingDAO();

    @GetMapping("/typing")
    public List fetchTypings(){
        return typingDAO.readAll();
    }

    @GetMapping("/typing/{id}")
    public Typing getTypingByID(@PathVariable("id") int typingID){
        return typingDAO.readByID(typingID);
    }

    @PostMapping("/typing")
    public int createTyping(@RequestBody Map<String, String> body){
        return (int)typingDAO.createObject(new Typing(body.get("name")));
    }

    @PostMapping("/typing/search")
    public List searchTypings(@RequestBody Map<String, String> body){
        return typingDAO.readByNameDesc(body.get("search"));
    }

    @PutMapping("/typing/{id}")
    public Typing updateTyping(@PathVariable("id") int typingID, @RequestBody Map<String, String> body){
        Typing typing= typingDAO.readByID(typingID);
        typing.setName(body.get("name"));
        return (Typing)typingDAO.updateObject(typing);
    }

    @DeleteMapping("/typing/{id}")
    public boolean deleteTyping(@PathVariable("id") int typingID){
        return typingDAO.deleteObject(typingDAO.readByID(typingID));
    }

}
