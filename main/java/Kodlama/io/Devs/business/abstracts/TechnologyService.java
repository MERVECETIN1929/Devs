package Kodlama.io.Devs.business.abstracts;

import Kodlama.io.Devs.business.request.TechnologyRequest;
import Kodlama.io.Devs.business.response.TechnologyResponse;
import java.util.List;

public interface TechnologyService {

    public List<TechnologyResponse> getAll();
    public String add(TechnologyRequest technologyRequest);
    public TechnologyResponse getById(long id) throws Exception;
    public void delete(long id);
    public void update(TechnologyRequest technologyRequest,long id);
}
