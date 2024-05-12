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
    }

    public void makeForm ( ) {
        makeForm( FORM_INSERT, new Od( ) );
    }

    // Método para construir o HTML do dropdown de pontos
    public String dropdownPonto ( List<Ponto> pontos ) 
    {
        String html = "";
        for( int i = 0; i < pontos.size( ); i++ ) {
            html += "<option value=\"" + pontos.get(i).getIdPonto() + "\">" + pontos.get(i).getLogradouro() + ", " + 
            pontos.get(i).getNumero( ) + ("</option>");
        }
        return ( html );
    }

    public String dropdownHorario( List<Horario> horarios ) 
    {
        String html = "";
    
        for( int i = 0; i < horarios.size( ); i++ ) {
            html += "<option value=\"" + horarios.get(i).getIdHorario() + "\">" + horarios.get(i).getHorario() + "</option>";
        }
        return ( html );
    }

    public String dropdownOnibus( List<Onibus> onibus ) 
    {
        String html = "";
        for( int i = 0; i < onibus.size( ); i++ ) {
            html += "<option value=\"" + onibus.get(i).getIdOnibus() + "\">" + onibus.get(i).getNumero( ) + "</option>";
        }
        return ( html );
    }

    public void makeForm( int tipo, Od od ) 
    {
        String nomeArquivo = "Codigo\\BackEnd\\form.html";
        form = "";
        try {
            Scanner entrada = new Scanner(new File(nomeArquivo));
            while (entrada.hasNext()) {
                form += (entrada.nextLine() + "\n");
            }
            entrada.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String umOd = "";
        if (tipo != FORM_INSERT) {
            umOd += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
            umOd += "\t\t<tr>";
            umOd += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/od/list/1\">Adicionar Origem-Destino</a></b></font></td>";
            umOd += "\t\t</tr>";
            umOd += "\t</table>";
            umOd += "\t<br>";
        }

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

            String dropdownOnibus  = dropdownOnibus ( onibusDAO.getAll ( ) );
            String dropdownPonto   = dropdownPonto  ( pontoDAO.getAll  ( ) );
            String dropdownHorario = dropdownHorario( horarioDAO.getAll( ) );

            // Aqui você pode incorporar os dropdowns ao formulário HTML
            form = form.replaceFirst("<UM-ONIBUS>", dropdownOnibus );
            form = form.replaceFirst("<UM-ORIGEM>", dropdownPonto );
            form = form.replaceFirst("<UM-DESTINO>", dropdownPonto );
            form = form.replaceFirst("<UM-HORARIO>", dropdownHorario );

        } 
        else if( tipo == FORM_DETAIL ) 
        {
/*             String umProduto = "";
            umProduto += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Produto (ID " + produto.getID() + ")</b></font></td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td>&nbsp;Descrição: "+ produto.getDescricao() +"</td>";
			umProduto += "\t\t\t<td>Preco: "+ produto.getPreco() +"</td>";
			umProduto += "\t\t\t<td>Quantidade: "+ produto.getQuantidade() +"</td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td>&nbsp;Data de fabricação: "+ produto.getDataFabricacao().toString() + "</td>";
			umProduto += "\t\t\t<td>Data de validade: "+ produto.getDataValidade().toString() + "</td>";
			umProduto += "\t\t\t<td>&nbsp;</td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t</table>"; */
        } 
        else {
            System.out.println( "ERRO! Tipo não identificado " + tipo );
        }
        form = form.replaceFirst( "<UM-OD>", umOd );

        String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Suas Notificações:</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
                "\n<tr>\n" + 
                "\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
                "\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
                "\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
                "</tr>\n";
		
		List<Od> produtos = odDAO.getAll( );

		int i = 0;
		String bgcolor = "";
		for( Od p : produtos ) 
        {
			bgcolor = ( i++ % 2 == 0 ) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
                    "\t<td>" + p.getLinha( ) + "</td>\n" +
                    "\t<td>" + p.getOrigem( ) + "</td>\n" +
                    "\t<td>" + p.getDestino( ) + "</td>\n" +
                    "\t<td>" + p.getHorario( ) + "</td>\n" +
                    "\t<td align=\"center\" valign=\"middle\"><a href=\"/od/" + p.getIdOd() + "\"><img src=\"Codigo/BackEnd/src/main/resources/public/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
                    "\t<td align=\"center\" valign=\"middle\"><a href=\"/od/update/" + p.getIdOd() + "\"><img src=\"Codigo/BackEnd/src/main/resources/public/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
                    // "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteProduto('" + p.getIdOd() + "', '" + p.getDescricao() + "', '" + p.getPreco() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
                    "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-OD>", list);
    } // end makeForm ( )
	
    public Object insert(Request request, Response response) 
    {
        String linha   = request.queryParams( "linha"   );
        String origem  = request.queryParams( "origem"  );
        String destino = request.queryParams( "destino" );
        String horario = request.queryParams( "horario" );
        String resp = "";
        
        Od od = new Od(-1, linha, origem, destino, horario);
        
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

	
	public Object get( Request request, Response response ) 
    {
		int id = Integer.parseInt(request.params(":id"));		
		Od produto = (Od) odDAO.getById( id );
		
		if( produto != null )
        {
			response.status( 200 ); // success
			makeForm( FORM_DETAIL, produto );
        } 
        else {
            response.status( 404 ); // 404 Not found
            String resp = "Od " + id + " não encontrado.";
            makeForm( );
            form.replaceFirst( "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">" );     
        }

		return form;
	} // end get ( )

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
	
	public Object getAll( Request request, Response response ) 
    {
		makeForm( );
        response.header("Content-Type", "text/html");
        response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
    public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Od od = odDAO.getById( id );
        String resp = "";       
    
        if (od != null) {
            od.setLinha(request.queryParams("linha"));
            od.setHorario(request.queryParams("horario"));
            od.setOrigem(request.queryParams("origem"));
            od.setDestino(request.queryParams("destino"));
            odDAO.update(od);
            response.status(200); // success
            resp = "Od (ID " + od.getIdOd() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Od (ID " + id + ") não encontrado!";
        }
        makeForm();
        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
    }
    
	public Object delete( Request request, Response response ) 
    {
        int id = Integer.parseInt(request.params(":id"));
        Od od = odDAO.getById( id );
        String resp = "";       

        if( od != null ) {
            odDAO.delete(id);
            response.status(200); // success
            resp = "Od (" + id + ") excluído!";
        } 
        else {
            response.status(404); // 404 Not found
            resp = "Od (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}
