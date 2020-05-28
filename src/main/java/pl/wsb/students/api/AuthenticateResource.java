package pl.wsb.students.api;

import pl.wsb.students.consts.ApiEndpoints;
import pl.wsb.students.model.AuthenticationRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ApiEndpoints.AUTHENTICATE)//globalna sciezka do zasobu
@Produces(MediaType.APPLICATION_JSON)//globalny format danych dla zaobu
@Consumes(MediaType.APPLICATION_JSON)//metoda http dla danego endpointa

//tu jest juz odwołanie do poszczegolnych modeli itd, każdy tam ma odpowiednio put, post itd.
//pierwszy, authentication ma format body i tak jest też przekazywany
public class AuthenticateResource {
    @POST
    public Response postAuthenticate(AuthenticationRequest body) {
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }
}
