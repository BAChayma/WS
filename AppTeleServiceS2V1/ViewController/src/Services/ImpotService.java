package Services;

import model.Data.Impot;
import model.Data.Response;

public interface ImpotService {
            public Response addImpot(Impot impot);
            
            public Response deleteImpot(int kimpot);
            
            public Impot getImpot(int kimpot);
            
            public Impot[] getAllImpots();
}
