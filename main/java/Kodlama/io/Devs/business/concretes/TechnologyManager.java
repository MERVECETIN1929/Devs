package Kodlama.io.Devs.business.concretes;

import Kodlama.io.Devs.business.abstracts.LanguageService;
import Kodlama.io.Devs.business.abstracts.TechnologyService;
import Kodlama.io.Devs.business.request.TechnologyRequest;
import Kodlama.io.Devs.business.response.LanguageResponse;
import Kodlama.io.Devs.business.response.TechnologyResponse;
import Kodlama.io.Devs.dataAccess.abstracts.LanguageRepository;
import Kodlama.io.Devs.dataAccess.abstracts.TechnologyRepository;
import Kodlama.io.Devs.entities.concretes.Language;
import Kodlama.io.Devs.entities.concretes.Technology;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechnologyManager implements TechnologyService {
    @Autowired
    private LanguageService languageService;
    @Autowired
    private TechnologyRepository technologyRepository;

    public TechnologyManager(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @Override
    public List<TechnologyResponse> getAll() {
        List<Technology> technology = technologyRepository.findAll();
        List<TechnologyResponse> technologyResponses = new ArrayList<TechnologyResponse>();
        for (Technology technology1 : technology) {
            TechnologyResponse technologyResponse = new TechnologyResponse();
            technologyResponse.setName(technology1.getName());
            technologyResponse.setLanguage_id(technology1.getLanguage().getId());
            technologyResponse.setLanguageName(technology1.getLanguage().getName());
            technologyResponses.add(technologyResponse);
        }
        return technologyResponses;

    }

    @Override
    public String add(TechnologyRequest technologyRequest) {
        // tekrar istemiyoruz ve kayıt ederken alınan language ismiyleeşleştiricez idyi yoksa kendi tekrardan yeni bir tane nesne oluşturuyor
        if(!technologyRequest.getName().isBlank()&& !technologyRequest.getName().isEmpty()){
            if(!isExist(technologyRequest) ){
            
              if(getNameLanguage(technologyRequest.getLanguage_name())==null){return "eklenmek istenilen dil bulunamadı";}
                
                Technology technology=new Technology();
                technology.setName(technologyRequest.getName());
                technology.setLanguage(getNameLanguage(technologyRequest.getLanguage_name()));
                technologyRepository.save(technology);
                return "ekleme başarılı";
            
           
            }
            return "aynı isimden alt teknolojiler eklenememeli ";
           
        }
        return " teknoloji ismi boş geçilemez";
    }

    @Override
    public TechnologyResponse getById(long id) throws Exception{
        Technology technology=technologyRepository.getById(id);
        if(technology==null){
            return null;
        }
        TechnologyResponse technologyResponse=new TechnologyResponse();
        technologyResponse.setLanguage_id(technology.getLanguage().getId());
        technologyResponse.setName(technology.getName());
        technologyResponse.setLanguageName(technology.getLanguage().getName());
        return technologyResponse;
    }

    @Override
    public void delete(long id) {
        technologyRepository.delete(technologyRepository.getById(id));
       
    }

    @Override
    public void update(TechnologyRequest technologyRequest, long id) {
       Technology technology=technologyRepository.getById(id);
       technology.setLanguage(getNameLanguage(technologyRequest.getLanguage_name()));
       technology.setName(technologyRequest.getName());
       technologyRepository.save(technology);
    }
    public boolean isExist(TechnologyRequest technologyRequest){
        for (Technology technology : technologyRepository.findAll()) {
            if(technologyRequest.getName().equals(technology.getName()))
            {
                return true;
            }
        }
        return false;
    }
    public Language getNameLanguage(String name){
        for (LanguageResponse languageResponse : languageService.getAll()) {
            if(languageResponse.getName().equals(name)){
                Language language=new Language();
                language.setId(languageResponse.getId());
                languageResponse.setName(languageResponse.getName());
                return language;
            }
        }
        return null;
    }

}
