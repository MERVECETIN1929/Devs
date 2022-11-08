//null => isNull
//“” => isEmpty
//“    “ => isBlank
package Kodlama.io.Devs.business.concretes;

import Kodlama.io.Devs.business.abstracts.LanguageService;
import Kodlama.io.Devs.business.request.LanguageRequest;
import Kodlama.io.Devs.business.response.LanguageResponse;
import Kodlama.io.Devs.dataAccess.abstracts.LanguageRepository;
import Kodlama.io.Devs.entities.concretes.Language;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageManager implements LanguageService {

    private LanguageRepository languageRepository;

    @Autowired
    public LanguageManager(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public String add(LanguageRequest languageRequest) {
        if(isExists(languageRequest)){
            return "aynı isimde ekleme yapılamaz";
        }
        else if(languageRequest.getName().isBlank()||languageRequest.getName().isEmpty()){
            return "isim kısmı boş geçilemez";
        }
        Language language=new Language();
        language.setName(languageRequest.getName());
        languageRepository.save(language);
        return "ekleme başarılı";

    }

    @Override
    public String delete(long id) {
        languageRepository.deleteById(id);
        return "silme başarılı";
    }

    @Override
    public ArrayList<LanguageResponse> getAll() {
        List<Language> language=languageRepository.findAll();
        ArrayList<LanguageResponse> languageResponses=new ArrayList<LanguageResponse>();
        for(Language language1:language){
            LanguageResponse languageResponse=new LanguageResponse();
            languageResponse.setId(language1.getId());
            languageResponse.setName(language1.getName());
            languageResponses.add(languageResponse);
        }
        return languageResponses;
    }

    @Override
    public String update(LanguageRequest languageRequest, long id) {
        if(isExists(languageRequest)){
            return "aynı isimde ekleme yapılamaz";
        }
        else if(languageRequest.getName().isBlank()||languageRequest.getName().isEmpty()){
            return "isim kısmı boş geçilemez";
        }
        Language language=languageRepository.getById(id);
        language.setName(languageRequest.getName());
        languageRepository.save(language);
        return "güncelleme başarılı";
        
    }

    @Override
    public LanguageResponse getById(long id) {
        Language language=languageRepository.getById(id);
        if(language==null){
            return new LanguageResponse();
        }
        LanguageResponse languageResponse=new LanguageResponse();
        languageResponse.setId(language.getId());
        languageResponse.setName(language.getName());
        return languageResponse;
    }
    public boolean isExists(LanguageRequest languageRequest){
        
        for (Language language1 : languageRepository.findAll()) {
            if(language1.getName().equals(languageRequest.getName())){
                return true;
            }
        }
        return false;
    }
}
