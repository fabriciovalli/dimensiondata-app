package com.dimensiondata.fabriciovalli;

import com.dimensiondata.fabriciovalli.model.Server;
import com.dimensiondata.fabriciovalli.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Scanner;

@SpringBootApplication
public class MainApp implements CommandLineRunner
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException
	{
		SpringApplication.run(MainApp.class, args);
	}

	public static Scanner in =  new Scanner(System.in);

	@Autowired
	ServerService service;


	public void run(String... args) throws Exception {
		boolean running = true;

		while(running)
		{
			showHelp();
			String option = in.next();

			if(option.equals("help"))
			{
				showHelp();
			}
			else if(option.equals("quit"))
			{
				running = false;
			}
			else if(option.equals("countServers"))
			{
				countServers();
			}
			else if(option.equalsIgnoreCase("addServer"))
			{
				addServer();
			}
			else if(option.equalsIgnoreCase("importServer"))
			{
				importServer();
			}
			else if(option.equalsIgnoreCase("deleteServer"))
			{
				deleteServer();
			}
			else if(option.equalsIgnoreCase("editServer"))
			{
				editServer();
			}
			else if(option.equalsIgnoreCase("listServers"))
			{
				listAllServers();
			}
		}
	}

	private void importServer() {
		ClassLoader classLoader = getClass().getClassLoader();
		try (InputStream is = new FileInputStream(classLoader.getResource("server_1.xml").getFile())){
			service.addServer(is);
			System.out.println("Servers imported successfully");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	private void listAllServers() {
		for (Server server: service.listAll()) {
			System.out.println(server);
		}
	}

	private void editServer() throws IOException {
		System.out.print("Please input the id of the server: ");
		Integer id = in.nextInt();
		System.out.print("Please input the new name for the server: ");
		String name = in.next();
		service.editServer(id, name);
		System.out.println("Server edited successfully");
	}

	private void deleteServer() throws IOException {
		System.out.print("Please input the id of the server that you want to delete: ");
		Integer id = in.nextInt();
		service.removeServer(id);
		System.out.println("Server removed successfully");
	}

	private void addServer() throws IOException {
		System.out.print("Please input the id of the server: ");
		Integer id = in.nextInt();
		System.out.print("Please input the name of the server: ");
		String name = in.next();
		service.addServer(id, name);
		System.out.println("Server added successfully");
	}

	private void countServers() {
		System.out.println("The server count is: " + service.getCount());
	}

	private void showHelp()
	{
		System.out.println("help to display this message");
		System.out.println("countServers to display the current number of servers present");
		System.out.println("addServer to add a new server");
		System.out.println("importServer to import server from file server_1.xml");
		System.out.println("editServer to change the name of a server identified by id (takes 2 additional args - the id and the new name)");
		System.out.println("deleteServer to delete a server (takes one more arg - the id of the server to delete)");
		System.out.println("listServers to display details of all servers servers");
	}
}
