package Kodlama.io.Devs.business.abstracts;

import Kodlama.io.Devs.entities.concretes.Language;
import java.util.ArrayList;

public interface LanguageService {
    public String add(Language language);
    public String  delete(int id);
    public ArrayList<Language> getAll();
    public String update(Language language,int id);
    public Language getById(int id);
}
