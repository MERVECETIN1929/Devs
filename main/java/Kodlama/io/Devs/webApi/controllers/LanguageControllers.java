package Kodlama.io.Devs.webApi.controllers;

import Kodlama.io.Devs.business.abstracts.LanguageService;
import Kodlama.io.Devs.business.request.LanguageRequest;
import Kodlama.io.Devs.business.response.LanguageResponse;
import Kodlama.io.Devs.entities.concretes.Language;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/languages")
public class LanguageControllers {
    private LanguageService languageService;
    @Autowired
    public LanguageControllers(LanguageService languageService){
        this.languageService=languageService;
    }
    @GetMapping("/getall")
    public ArrayList<LanguageResponse> getAll(){
        return languageService.getAll();
    }
    @PostMapping("/add")
    public String add(@RequestBody LanguageRequest languageRequest){
        
         return this.languageService.add(languageRequest);
    }
    @GetMapping("/getbyid/{id}")
    public LanguageResponse getById(@PathVariable int id){
        return languageService.getById(id);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        return languageService.delete(id);
    }
    @PutMapping("/update/{id}")
    public String update(@RequestBody LanguageRequest languageRequest,@PathVariable int id) {
       return languageService.update(languageRequest, id);
    }
}
