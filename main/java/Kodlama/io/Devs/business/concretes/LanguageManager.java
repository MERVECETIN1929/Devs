//null => isNull
//“” => isEmpty
//“    “ => isBlank
package Kodlama.io.Devs.business.concretes;

import Kodlama.io.Devs.business.abstracts.LanguageService;
import Kodlama.io.Devs.dataAccess.abstracts.LanguageRepository;
import Kodlama.io.Devs.entities.concretes.Language;
import java.util.ArrayList;
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
    public String add(Language language) {
        if(isExists(language)){
            return "aynı isimde ekleme yapılamaz";
        }
        else if(language.getName().isBlank()||language.getName().isEmpty()){
            return "isim kısmı boş geçilemez";
        }
        languageRepository.add(language);
        return "ekleme başarılı";

    }

    @Override
    public String delete(int id) {
        return languageRepository.delete(id);
    }

    @Override
    public ArrayList<Language> getAll() {
        return languageRepository.getAll();
    }

    @Override
    public String update(Language language, int id) {
        if(isExists(language)){
            return "aynı isimde ekleme yapılamaz";
        }
        else if(language.getName().isBlank()||language.getName().isEmpty()){
            return "isim kısmı boş geçilemez";
        }
        return languageRepository.update(language, id);
        
    }

    @Override
    public Language getById(int id) {
        return languageRepository.getById(id);
    }
    public boolean isExists(Language language){
        for (Language language1 : languageRepository.getAll()) {
            if(language1.getName().equals(language.getName())){
                return true;
            }
        }
        return false;
    }
}
