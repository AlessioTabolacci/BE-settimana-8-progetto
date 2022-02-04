package it.contocorrente.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import it.contocorrente.entity.ContoCorrente;
import it.contocorrente.entity.Movimento;
import it.contocorrente.entity.TipoOperazione;



@Path("/conto")
public class ContoCorrenteRest {

	private static ArrayList<Movimento> movimenti = new ArrayList<>();
	private static ArrayList<ContoCorrente> conti = new ArrayList<>();

	//http://localhost:8080/Ewallet/rest/conto

	@POST
	@Path ("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response creaConto(ContoCorrente c) {
		conti.add(c);
		return Response.status(200).entity("Conto creato correttamente!").build();

	}
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cancellaConto(ContoCorrente c) {
		for(ContoCorrente con : conti ) {
			if(con.getIban().equals(c.getIban())) {
				conti.remove(con);
				return Response.status(200).entity("Conto rimosso correttamente!").build();

			}
		}
		return Response.status(404).entity("Conto non trovato!").build();	


	}


	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response aggiornaConto(ContoCorrente c) {
		for(ContoCorrente con : conti) {
			if(con.getIban().equals(c.getIban())) {
				int index = conti.lastIndexOf(con);
				conti.set(index, c);
				return Response.status(200).entity("Conto aggiornato correttamente!").build();
			}
		}
		return Response.status(404).entity("Aggiornamento non riuscito!").build();

	}
	@PUT
	@Path("/movimento")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response movimento(Movimento m) {

		for (ContoCorrente con : conti) {
			if(con.getIban().equals(m.getIban())) {

				if (m.getTipo().equals(TipoOperazione.PRELIEVO)) {
					if(m.getImporto() > con.getSaldo() || m.getImporto() < 0) {
						return Response.status(406).entity("impossibile prelevare").build();	
					}
					double nuovoSaldo = con.getSaldo()-m.getImporto();
					con.setSaldo(nuovoSaldo);
					movimenti.add(m);
					return Response.status(200).entity("Operazione riuscita! Il nuovo saldo è: " + nuovoSaldo).build();
				}
				if (m.getTipo().equals(TipoOperazione.VERSAMENTO)) {
					if(m.getImporto() < 0) {
						return Response.status(406).entity("Operazione non riuscita! Inserire un importo corretto!").build();
					}
					double nuovoSaldo = con.getSaldo() + m.getImporto();
					con.setSaldo(nuovoSaldo);
					movimenti.add(m);
					return Response.status(200).entity("Operazione riuscita! Il nuovo saldo è: " + nuovoSaldo).build();
				}
			}
		}
		return Response.status(404).entity("operazione non riuscita!").build();
	}


	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movimento> getAllMovimenti() {

		return movimenti;
	}

	@GET
	@Path("/conti")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ContoCorrente> getAllConti() {

		return conti;
	}



}
