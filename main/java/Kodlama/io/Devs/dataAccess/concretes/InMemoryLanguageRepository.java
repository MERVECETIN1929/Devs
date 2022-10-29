package Kodlama.io.Devs.dataAccess.concretes;

import Kodlama.io.Devs.dataAccess.abstracts.LanguageRepository;
import Kodlama.io.Devs.entities.concretes.Language;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryLanguageRepository implements LanguageRepository {

    private ArrayList<Language> languageList;

    public InMemoryLanguageRepository() {
        languageList = new ArrayList<>();
        languageList.add(new Language(1, "JAVA"));
        languageList.add(new Language(2, "ASEMBLY"));
        languageList.add(new Language(3, "C"));
        languageList.add(new Language(4, "PYTHON"));
        languageList.add(new Language(5, "ADA"));
    }

    @Override
    public void add(Language language) {
        languageList.add(language);

    }

    @Override
    public String delete(int id) {
        // zaten halihazırda bir gtbyıd var onu kullanıp gelen id kimin bulabiliriz
        Language language = getById(id);

        if (languageList.remove(language)) {

            return "silme işlemi başarılı";
        }

        return "silme işlemi başarız";
    }

    @Override
    public String update(Language language, int id) {
        Language language1 = getById(id);
        // id de setli olduğundan postmandan onu da almalıyız :S
        language1.setId(language.getId());
        language1.setName(language.getName());

        return "güncelleme başarılı";
    }

    @Override
    public ArrayList<Language> getAll() {
        return languageList;
    }

    @Override
    public Language getById(int id) {
        for (Language language : languageList) {
            if (language.getId() == id) {
                return language;
            }
        }
        return null;
    }

}
