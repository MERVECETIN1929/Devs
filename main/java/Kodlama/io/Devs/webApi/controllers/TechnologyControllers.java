package Kodlama.io.Devs.webApi.controllers;

import Kodlama.io.Devs.business.abstracts.TechnologyService;
import Kodlama.io.Devs.business.request.TechnologyRequest;
import Kodlama.io.Devs.business.response.TechnologyResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/technology")
public class TechnologyControllers {
    private TechnologyService technologyService;
    @Autowired
    public TechnologyControllers(TechnologyService technologyService){
        this.technologyService=technologyService;
    }
    @GetMapping
    public List<TechnologyResponse> getAll(){
        return technologyService.getAll();
    }
    @PostMapping("/add")
    public String add(@RequestBody TechnologyRequest technologyRequest){
        return technologyService.add(technologyRequest);
    }
    @GetMapping("/{id}")
    public TechnologyResponse getById(@PathVariable long id) throws Exception{
        return technologyService.getById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
         technologyService.delete(id);
    }
    @PutMapping("/updateTechnology/{id}")
    public void update(@PathVariable long id, @RequestBody TechnologyRequest technologyRequest){
        technologyService.update(technologyRequest, id);
    }
}
