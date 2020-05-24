package Services;

import model.Data.FormeJuridique;
import model.Data.Impot;
import model.Data.Response;

public interface FormeJuridiqueService {
    public Response addFJ(FormeJuridique FJ);
    
    public Response deleteFJ(int kFormJuri);
    
    public FormeJuridique getFJ(int kFormJuri);
    
    public FormeJuridique[] getAllFJ();
}
