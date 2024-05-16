package service;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import spark.Request;
import spark.Response;

import model.Od;
import dao.OdDAO;
import model.Horario;
import dao.HorarioDAO;
import model.Ponto;
import dao.PontoDAO;
import model.Onibus;
import dao.OnibusDAO;

public class OdService2 
{
    private OdDAO      odDAO      = new OdDAO( );
    private PontoDAO   pontoDAO   = new PontoDAO( );
    private HorarioDAO horarioDAO = new HorarioDAO( );
    private OnibusDAO  onibusDAO  = new OnibusDAO( );
    private String    form;
    private final int FORM_INSERT = 1;
    private final int FORM_DETAIL = 2;
    private final int FORM_UPDATE = 3;

    public OdService2 ( ) {
        makeForm( );
    } // end OdService2 ( )

    public void makeForm ( ) {
        makeForm( FORM_INSERT, new Od( ) );
    } // end makeForm ( )

    public void makeForm( int tipo, Od od ) 
    {
        String nomeArquivo = "Codigo\\FrontEnd\\notificacoes.html";
        form = "";
        try 
        {
            Scanner entrada = new Scanner(new File(nomeArquivo));
            while (entrada.hasNext( )) {
                form += (entrada.nextLine() + "\n");
            }
            entrada.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        /* Criar o HTML para inserir ou atualizar um OD  */
        String umOd = "";
        if (tipo == FORM_INSERT || tipo == FORM_UPDATE) 
        {
            String action = "/od/";
            String name, buttonLabel;
            if( tipo == FORM_INSERT ) 
            {
                action += "insert";
                name = "Inserir Origem-Destino";
                buttonLabel = "Inserir";
            } 
            else 
            {
                action += "update/" + od.getIdOd();
                name = "Atualizar Origem-Destino ( ID " + od.getIdOd() + " )";
                buttonLabel = "Atualizar";
            }
            /* Substituir a marcação <UM-ACTION> pelo HTML do OD */
            form = form.replaceFirst( "<UM-ACTION>", action );
            
            String dropdownOnibus  = dropdownOnibus ( onibusDAO.getAll ( ) );
            String dropdownPonto   = dropdownPonto  ( pontoDAO.getAll  ( ) );
            String dropdownHorario = dropdownHorario( horarioDAO.getAll( ) );

            form = form.replaceFirst("<UM-ONIBUS>", dropdownOnibus );
            form = form.replaceFirst("<UM-ORIGEM>", dropdownPonto );
            form = form.replaceFirst("<UM-DESTINO>", dropdownPonto );
            form = form.replaceFirst("<UM-HORARIO>", dropdownHorario );
        }
        else {
            System.out.println( "ERRO! Tipo não identificado " + tipo );
        }
        
        /* Substituir a marcação <UM-OD> pelo HTML do OD */
        form = form.replaceFirst( "<UM-OD>", umOd );
        
        /* Criar um HTML para listar os ODs */
        /* Criar o cabeçalho da tabela */
        String list = "\n<h3>Suas Notificações:</h3>\n";
        list += "<table width=\"100%\" align=\"center\" bgcolor=\"#98dc85\" class=\"my-3\" >";
        list += "\n<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +"\n<tr>\n" + 
                "\t<td width=\"100\" align=\"center\"><b>Linha</b></td>\n" +
                "\t<td width=\"100\" align=\"center\"><b>Origem</b></td>\n" +
                "\t<td width=\"100\" align=\"center\"><b>Destino</b></td>\n" +
                "\t<td width=\"100\" align=\"center\"><b>Horário</b></td>\n" +
                "\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
                "\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
                "</tr>\n";
		List<Od> produtos = odDAO.getAll( );
        /* Criar a tabela com os ODs */
		int i = 0;
		String bgcolor = "";
		for( Od p : produtos ) 
        {
			bgcolor = ( i++ % 2 == 0 ) ? "#e5f6e0" : "#cbeec2";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
                    "\t<td width=\"100\" align=\"center\">" + p.getLinha( ) + "</td>\n" +
                    "\t<td width=\"100\" align=\"center\">" + p.getOrigem( ) + "</td>\n" +
                    "\t<td width=\"100\" align=\"center\">" + p.getDestino( ) + "</td>\n" +
                    "\t<td width=\"100\" align=\"center\">" + p.getHorario( ) + "</td>\n" +
                    "\t<td align=\"center\" valign=\"middle\"><a href=\"/od/update/" + p.getIdOd() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
                    "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:deletarOd('" + p.getIdOd() + "', '" + p.getOrigem() + "', '" + p.getDestino() + "', '" + p.getHorario() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
                    "</tr>\n";
		}
		list += "</table>";
        /* Substituir a marcação <LISTAR-OD> pelo HTML da lista de ODs */
		form = form.replaceFirst("<LISTAR-OD>", list);
    } // end makeForm ( )

    // Método para construir o HTML do dropdown
    public String dropdownPonto ( List<Ponto> pontos ) 
    {
        String html = "";
        for( int i = 0; i < pontos.size( ); i++ ) {
            html += "<option value=\"" + pontos.get(i).getIdPonto() + "\">" + pontos.get(i).getLogradouro() + ", " + 
            pontos.get(i).getNumero( ) + ("</option>");
        }
        return ( html );
    } // end dropdownPonto ( )
    
    // Método para construir o HTML do dropdown
    public String dropdownHorario( List<Horario> horarios ) 
    {
        String html = "";
        for( int i = 0; i < horarios.size( ); i++ ) {
            html += "<option value=\"" + horarios.get(i).getIdHorario() + "\">" + horarios.get(i).getHorario() + "</option>";
        }
        return ( html );
    } // end dropdownHorario ( )
    
    // Método para construir o HTML do dropdown
    public String dropdownOnibus( List<Onibus> onibus ) 
    {
        String html = "";
        for( int i = 0; i < onibus.size( ); i++ ) {
            html += "<option value=\"" + onibus.get(i).getIdOnibus() + "\">" + onibus.get(i).getNumero( ) + "</option>";
        }
        return ( html );
    } // end dropdownOnibus ( )
    
    // inserir um novo OD
    public Object insert(Request request, Response response) 
    {
        String linha   = request.queryParams( "linha"   );
        String origem  = request.queryParams( "origem"  );
        String destino = request.queryParams( "destino" );
        String horario = request.queryParams( "horario" );
        String resp = "";

        Od od = new Od( -1, linha, origem, destino, horario);

        if( odDAO.insert(od) == true ) 
        {
            resp = "Origem-Destino (" + linha + " - " +origem + " - " + destino + " - " + horario + ") inserido!";
            response.status(201); // 201 Created
        } 
        else 
        {
            resp = "Origem-Destino (" + linha + " - " +origem + " - " + destino + " - " + horario + ") não inserido!";
            response.status(404); // 404 Not found
        }
        makeForm( );
        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
    } // end insert ( )

	// obter um OD
	public Object get( Request request, Response response ) 
    {
		int id = Integer.parseInt(request.params(":id"));		
		Od produto = (Od) odDAO.getById( id );
		if( produto != null )
        {
			response.status( 200 ); // success
			makeForm( FORM_DETAIL, produto );
        } 
        else 
        {
            response.status( 404 ); // 404 Not found
            String resp = "Od " + id + " não encontrado.";
            makeForm( );
            form.replaceFirst( "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">" );     
        }
		return form;
	} // end get ( )

    // obter um OD para atualizar
	public Object getToUpdate( Request request, Response response ) 
    {
		int id = Integer.parseInt(request.params(":id"));		
		Od od = (Od) odDAO.getById(id);
		if( od != null ) 
        {
			response.status( 200 ); // success
			makeForm( FORM_UPDATE, od );
        } 
        else 
        {
            response.status( 404 ); // 404 Not found
            String resp = "Od " + id + " não encontrado.";
            makeForm( );
            form.replaceFirst( "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">" );     
        }
		return form;
	} // end getToUpdate ( )
	
    // obter todos os ODs
	public Object getAll( Request request, Response response ) 
    {
		makeForm( );
        response.header("Content-Type", "text/html");
        response.header("Content-Encoding", "UTF-8");
		return form;
	} // end getAll ( )	
	
    // atualizar um OD
    public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Od od = odDAO.getById( id );
        String resp = "";       
        if (od != null)
        {
            od.setLinha(request.queryParams("linha"));
            od.setHorario(request.queryParams("horario"));
            od.setOrigem(request.queryParams("origem"));
            od.setDestino(request.queryParams("destino"));
            odDAO.update(od);
            response.status(200); // success
            resp = "Od (ID " + od.getIdOd() + ") atualizado!";
        } 
        else 
        {
            response.status(404); // 404 Not found
            resp = "Od (ID " + id + ") não encontrado!";
        }
        makeForm( );
        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
    } // end update ( )
    
    // excluir um OD
	public Object delete( Request request, Response response ) 
    {
        int id = Integer.parseInt(request.params(":id"));
        Od od = odDAO.getById( id );
        String resp = "";       
        if( od != null ) 
        {
            odDAO.delete(id);
            response.status(200); // success
            resp = "Od (" + id + ") excluído!";
        } 
        else 
        {
            response.status(404); // 404 Not found
            resp = "Od (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	} // end delete ( )
} // end class OdService2
