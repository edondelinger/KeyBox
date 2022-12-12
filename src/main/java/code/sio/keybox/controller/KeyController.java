package code.sio.keybox.controller;

import code.sio.keybox.model.Key;
import code.sio.keybox.model.KeyDAO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class KeyController {

    @RequestMapping(value = "/keys", method = RequestMethod.GET)
    public ArrayList<Key> getAllKeys() {
        KeyDAO kd = new KeyDAO();
        return kd.getAllKeys();
    }

    @PostMapping("/add")
    boolean newKey(@RequestBody Key newKey) {
        KeyDAO kd = new KeyDAO();
        return kd.createKey(newKey);
    }

    @GetMapping("/key/{id}")
    Key one(@PathVariable int id) {
        KeyDAO kd = new KeyDAO();
        return kd.getAKey(id);
    }

    @PutMapping("/key/{id}")
    boolean replaceKey(@RequestBody Key newKey, @PathVariable int id) {
        KeyDAO kd = new KeyDAO();
        return kd.updateKey(id, newKey);
    }

    @DeleteMapping("/key/{id}")
    boolean deleteKey(@PathVariable int id) {
        KeyDAO kd = new KeyDAO();
        return kd.deleteKey(id);
    }
}