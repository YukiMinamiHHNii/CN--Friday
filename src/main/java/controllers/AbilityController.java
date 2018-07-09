package controllers;

import dao.AbilityDAO;
import entities.Ability;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Friday on 08/07/2018.
 */
@RestController
public class AbilityController {

    private AbilityDAO abilityDAO= new AbilityDAO();

    @GetMapping("/ability")
    public List fetchAbilities(){
        return abilityDAO.readAll();
    }

    @GetMapping("/ability/{id}")
    public Ability getAbilityByID(@PathVariable("id") int abilityID){
        return abilityDAO.readByID(abilityID);
    }

    @PostMapping("/ability")
    public int createAbility(@RequestBody Map<String, String> body){
        return (int)abilityDAO.createObject(new Ability(body.get("name"), body.get("description")));
    }

    @PostMapping("/ability/search")
    public List searchAbilities(@RequestBody Map<String, String> body){
        return abilityDAO.readByNameDesc(body.get("search"));
    }

    @PutMapping("/ability/{id}")
    public Ability updateAbility(@PathVariable("id") int abilityID, @RequestBody Map<String, String> body){
        Ability ability= abilityDAO.readByID(abilityID);
        ability.setName(body.get("name"));
        ability.setDescription(body.get("description"));
        return (Ability)abilityDAO.updateObject(ability);
    }

    @DeleteMapping("/ability/{id}")
    public boolean deleteAbility(@PathVariable("id") int abilityID){
        return abilityDAO.deleteObject(abilityDAO.readByID(abilityID));
    }

}
