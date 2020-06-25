package com.cliente.action;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import com.cibertec.entidad.Cliente;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("dawi")
public class ClienteAction extends ActionSupport{
	
	private String URL_CLIENTE="http://localhost:8080/WS_REST_Servidor_Modelo/rest/cliente/";
	//Mantenimiento
		private Cliente cliente;
		
		//Consulta
		private Cliente[] listaCliente;
		private int edad1;
		private int edad2;
		
		//Clase que acepta peticiones rest
		private HttpClient httpClient;
	
		//
		public ClienteAction() {
			httpClient=HttpClientBuilder.create().build();
		}

		
		@Action(value="/saveCliente",results= {@Result(name="ok",type="redirect",
				location="/cliente.jsp")})
		public String saveCliente() throws ClientProtocolException, IOException {
				//
				System.out.println("Guardando cliente");
				Gson gson = new Gson();
				String dataJSON;
				dataJSON=gson.toJson(cliente);
				System.out.println(dataJSON);
				//Definir metodo post
				HttpPost httpPost=new HttpPost(URL_CLIENTE+"registrar");
				//respuesta
				StringEntity string = new StringEntity(dataJSON.toString(),"UTF-8");
				httpPost.setHeader("Content-Type","application/json;charset UTF-8");
				httpPost.setEntity(string);
				//obtener el valor de la respuesta
				HttpResponse httpResponse=httpClient.execute(httpPost);
				//dataMensaje=EntityUtils.toString(httpResponse.getEntity());
				return  "ok";
				
			}
		
		
		@Action(value="/listAllClientes",results= {@Result(name="ok",type="json")})
		public String listAllClientes() throws ClientProtocolException, IOException {
			System.out.println("Listando cliente");
			//definir método "GET"
			HttpGet httpGet=new HttpGet(URL_CLIENTE+"consulta/"+edad1+"/"+edad2);
			//respuesta
			HttpResponse httpResponse=httpClient.execute(httpGet);
			//obtener el valor de la respuesta
			String json=EntityUtils.toString(httpResponse.getEntity());
			//
			Gson gson=new Gson();
			listaCliente=gson.fromJson(json, Cliente[].class);
			System.out.println(listaCliente);
			return "ok";
		}


		public Cliente getCliente() {
			return cliente;
		}


		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}


		public Cliente[] getListaCliente() {
			return listaCliente;
		}


		public void setListaCliente(Cliente[] listaCliente) {
			this.listaCliente = listaCliente;
		}


		public int getEdad1() {
			return edad1;
		}


		public void setEdad1(int edad1) {
			this.edad1 = edad1;
		}


		public int getEdad2() {
			return edad2;
		}


		public void setEdad2(int edad2) {
			this.edad2 = edad2;
		}
		
		
		
		
		
}






