package Kodlama.io.Devs.business.abstracts;

import Kodlama.io.Devs.business.request.LanguageRequest;
import Kodlama.io.Devs.business.response.LanguageResponse;
import Kodlama.io.Devs.entities.concretes.Language;
import java.util.ArrayList;

public interface LanguageService {
    public String add(LanguageRequest languageRequest);
    public String  delete(long id);
    public ArrayList<LanguageResponse> getAll();
    public String update(LanguageRequest languageRequest,long id);
    public LanguageResponse getById(long id);
}
