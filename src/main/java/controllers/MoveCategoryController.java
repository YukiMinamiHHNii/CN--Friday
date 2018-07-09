package controllers;

import dao.MoveCategoryDAO;
import entities.MoveCategory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Friday on 08/07/2018.
 */
@RestController
public class MoveCategoryController {

    private MoveCategoryDAO moveCatDAO= new MoveCategoryDAO();

    @GetMapping("/move-category")
    public List fetchMoveCats(){
        return moveCatDAO.readAll();
    }

    @GetMapping("/move-category/{id}")
    public MoveCategory getMoveCatByID(@PathVariable("id") int moveCatID){
        return moveCatDAO.readByID(moveCatID);
    }

    @PostMapping("/move-category")
    public int createMoveCat(@RequestBody Map<String, String> body){
        return (int)moveCatDAO.createObject(new MoveCategory(body.get("name")));
    }

    @PostMapping("/move-category/search")
    public List searchMoveCats(@RequestBody Map<String, String> body){
        return moveCatDAO.readByNameDesc(body.get("search"));
    }

    @PutMapping("/move-category/{id}")
    public MoveCategory updateMoveCat(@PathVariable("id") int moveCatID, @RequestBody Map<String, String> body){
        MoveCategory moveCat= moveCatDAO.readByID(moveCatID);
        moveCat.setName(body.get("name"));
        return (MoveCategory)moveCatDAO.updateObject(moveCat);
    }

    @DeleteMapping("/move-category/{id}")
    public boolean deleteMoveCat(@PathVariable("id") int moveCatID){
        return moveCatDAO.deleteObject(moveCatDAO.readByID(moveCatID));
    }

}