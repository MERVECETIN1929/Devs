
package Kodlama.io.Devs.dataAccess.abstracts;

import Kodlama.io.Devs.entities.concretes.Language;
import java.util.ArrayList;


public interface LanguageRepository {
    public void add(Language language);
    public String delete(int id);
    public String update(Language language,int id);
    public ArrayList<Language> getAll();
    public Language getById(int id);
}
