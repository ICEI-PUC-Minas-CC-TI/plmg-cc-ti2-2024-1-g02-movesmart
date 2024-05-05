/* 
 * 
 * 	EM TESTE
 */

/* package service;

import java.io.File;
import java.util.Scanner;
import model.Od;
import dao.OdDAO;
import spark.Request;
import spark.Response;

public class OdService 
{
    private OdDAO odDAO = new OdDAO();
    private String form;

    private final int FORM_INSERT = 1;
    private final int FORM_DETAIL = 2;
    private final int FORM_UPDATE = 3;

    public OdService ( ) {
        makeForm( );
    }

    public void makeForm ( ) {
        makeForm ( FORM_INSERT, new Od( ) );
    }

    public void makeForm ( int tipo, Od od ) 
	{
        String nomeArquivo = "C:\\Users\\vinic\\Desktop\\plmg-cc-ti2-2024-1-g02-movesmart\\Codigo\\BackEnd\\src\\main\\java\\service\\form.html";
        form = "";
        try 
		{
            Scanner entrada = new Scanner( new File( nomeArquivo ) );
            while ( entrada.hasNext( ) ) {
                form += ( entrada.nextLine( ) + "\n" );
            }
            entrada.close();
        } 
		catch( Exception e ) { System.out.println(e.getMessage()); }

        if( tipo != FORM_INSERT ) {
            form += createTable( );
        }

		if( tipo == FORM_INSERT || tipo == FORM_UPDATE ) 
		{
			String actionForm = createActionForm(tipo, od);
			form = form.replaceFirst("<UM-OD>", actionForm);
		}
    }

    private String createTable ( ) 
	{
        String  table = "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
				table += "\t\t<tr>";
				table += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/usuario/list/1\">Adicionar Origem-Destino</a></b></font></td>";
				table += "\t\t</tr>";
				table += "\t</table>";
				table += "\t<br>";
        return (table);
    }

    private String createActionForm ( int actionType, Od Od) {
        String action = "/Od/";
        String name, buttonLabel;
        
		if( actionType == FORM_INSERT ) 
		{
            action += "insert";
            name = "Inserir Od";
            buttonLabel = "Inserir";
        } 
		else if( actionType == FORM_UPDATE ) 
		{
            action += "update/" + Od.getIdOd();
            name = "Atualizar Od ( ID " + Od.getIdOd() + ")";
            buttonLabel = "Atualizar";
        } 
		else {
            return "";
        }

        String  formFields = "\t\t<tr>";
				formFields += "\t\t\t<td>&nbsp;Origem: <input class=\"input--register\" type=\"text\" name=\"origem\" value=\"\"></td>";
				formFields += "\t\t\t<td>Destino: <input class=\"input--register\" type=\"text\" name=\"destino\" value=\"\"></td>";
				formFields += "\t\t\t<td>Horário: <input class=\"input--register\" type=\"text\" name=\"horario\" value=\"\"></td>";
				formFields += "\t\t</tr>";

        String  form = "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
				form += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
				form += "\t\t<tr>";
				form += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
				form += "\t\t</tr>";
				form += formFields;
				form += "\t\t<tr>";
				form += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\"" + buttonLabel + "\" class=\"input--main__style input--button\"></td>";
				form += "\t\t</tr>";
				form += "\t</table>";
				form += "\t</form>";
        return (form);
	} // end createActionForm ( )

    public Object insert(Request request, Response response) {
        String origem = request.queryParams("origem");
        String destino = request.queryParams("destino");
        String horario = request.queryParams("horario");

        String resp = "";

        Od od = new Od( origem, destino, horario );

        if( odDAO.insert(od) ) {
            resp = "Od (" + origem + ") inserido!";
            response.status(201); // 201 Created
        } else {
            resp = "Od (" + origem + ") não inserido!";
            response.status(404); // 404 Not found
        }

        makeForm();
        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
    }
	
    public Object get(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Od od = odDAO.getById(id);

        if (od != null) {
            response.status(200); // success
            makeForm(FORM_DETAIL, od);
        } else {
            response.status(404); // 404 Not found
            String resp = "Od " + id + " não encontrado.";
            makeForm();
            form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
        }
        return form;
    }

    public Object getToUpdate(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Od od = odDAO.getById(id);

        if (od != null) {
            response.status(200); // success
            makeForm(FORM_UPDATE, od);
        } else {
            response.status(404); // 404 Not found
            String resp = "Od " + id + " não encontrado.";
            makeForm();
            form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
        }
        return form;
    }

    public Object getAll(Request request, Response response) {
        makeForm();
        response.header("Content-Type", "text/html");
        response.header("Content-Encoding", "UTF-8");
        return form;
    }

    public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Od od = odDAO.getById(id);
        String resp = "";

        if (od != null) {
            od.setOrigem(request.queryParams("origem"));
            od.setDestino(request.queryParams("destino"));
            od.setHorario(request.queryParams("horario"));
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

    public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Od od = odDAO.getById(id);
        String resp = "";

        if (od != null) {
            odDAO.delete(id);
            response.status(200); // success
            resp = "Od (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Od (" + id + ") não encontrado!";
        }
        makeForm();
        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
    }
}
 */