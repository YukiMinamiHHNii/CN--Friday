package controllers;

import dao.BattleFormatDAO;
import entities.BattleFormat;
import entities.SpeciesEntry;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

/**
 * Friday on 08/07/2018.
 */
@RestController
public class BattleFormatController {

    private BattleFormatDAO battleFormatDAO= new BattleFormatDAO();

    @GetMapping("/battle-format")
    public List fetchBattleFormat(){ return battleFormatDAO.readAllExcerpt(); }

    @GetMapping("/battle-format/{id}")
    public BattleFormat getBattleFormatByID(@PathVariable("id") int battleFormatID){
        return battleFormatDAO.readByID(battleFormatID);
    }

    @PostMapping("/battle-format")
    public int createBattleFormat(@RequestBody Map<String, Object> body) throws IOException {
        String name= (String)body.get("name");
        String desc= (String)body.get("description");
        Set<SpeciesEntry> species= parseSpeciesEntry((List)body.get("restrictedSpecies"));

        return (int)battleFormatDAO.createObject(new BattleFormat(name, desc, species));
    }

    @PostMapping("/battle-format/search")
    public List searchBattleFormats(@RequestBody Map<String, String> body){
        return battleFormatDAO.readByNameDesc(body.get("search"));
    }

    @PutMapping("/battle-format/{id}")
    public BattleFormat updateBattleFormat(@PathVariable("id") int battleFormatID, @RequestBody Map<String, Object> body){
        BattleFormat battleFormat= battleFormatDAO.readByID(battleFormatID);

        battleFormat.setName((String) body.get("name"));
        battleFormat.setDescription((String) body.get("description"));
        battleFormat.setRestrictedSpecies(parseSpeciesEntry((List) body.get("restrictedSpecies")));

        return (BattleFormat)battleFormatDAO.updateObject(battleFormat);
    }

    @DeleteMapping("/battle-format/{id}")
    public boolean deleteBattleFormat(@PathVariable("id") int battleFormatID){
        return battleFormatDAO.deleteObject(battleFormatDAO.readByID(battleFormatID));
    }

    public Set<SpeciesEntry> parseSpeciesEntry(List entryData){
        Set<SpeciesEntry> species= new HashSet<>();
        HashMap<String, Object> data;

        for(Object obj: entryData){
            data= (HashMap<String, Object>) obj;
            species.add(new SpeciesEntry((String)data.get("speciesID"), (String)data.get("name")));
        }

        return species;
    }

}
