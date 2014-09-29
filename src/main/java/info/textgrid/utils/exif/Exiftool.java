package info.textgrid.utils.exif;

import info.textgrid.namespaces.metadata.core._2010.MetadataContainerType;
import info.textgrid.namespaces.middleware.tgcrud.services.tgcrudservice.AuthFault;
import info.textgrid.namespaces.middleware.tgcrud.services.tgcrudservice.IoFault;
import info.textgrid.namespaces.middleware.tgcrud.services.tgcrudservice.MetadataParseFault;
import info.textgrid.namespaces.middleware.tgcrud.services.tgcrudservice.ObjectNotFoundFault;
import info.textgrid.namespaces.middleware.tgcrud.services.tgcrudservice.ProtocolNotImplementedFault;
import info.textgrid.namespaces.middleware.tgcrud.services.tgcrudservice.TGCrudService;
import info.textgrid.namespaces.middleware.tgcrud.services.tgcrudservice.tgcrudclient.TGCrudClientUtilities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.activation.DataHandler;
import javax.xml.bind.JAXB;
import javax.xml.ws.Holder;

public class Exiftool {
	
	static String sid = "RzyXNl5mVx7tZMsmus6ilIV7TG9cGFBZ6vYv59xlwhaLmPBfjpu4rTb3NJnw1411999202490103";
	static String pid = "TGPR-9b18459d-3a6b-004f-b6aa-4fba2b9e1d3e";
	
	public static void main(String[] args) throws IOException, MetadataParseFault, ObjectNotFoundFault, IoFault, AuthFault, ProtocolNotImplementedFault {
		// TODO Auto-generated method stub
		System.out.println("start");
//		SesameClient sc = new SesameClient("http://textgrid-esx1.gwdg.de/1.0/triplestore/textgrid-nonpublic");
//		sc.sparql("PREFIX ore:<http://www.openarchives.org/ore/terms/> PREFIX tg:<http://textgrid.info/relation-ns#> SELECT  ?URI WHERE {}")
		
		FileInputStream fis = new FileInputStream("/home/mathias/devel/prescaler/urilist.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		TGCrudService tgcrud = TGCrudClientUtilities.getTgcrud("https://textgridlab.org/1.0/tgcrud/TGCrudService");
		
		String line = br.readLine();
		
		
		
		while(line != null){
			System.out.println(line);
			
			Holder<DataHandler> tgObjectData = new Holder<DataHandler>();
			Holder<MetadataContainerType> tgObjectMetadata = new Holder<MetadataContainerType>();
			
			tgcrud.read(sid, "", line, tgObjectMetadata, tgObjectData);
	
			tgcrud.create(sid, "", line, true, pid, tgObjectMetadata, tgObjectData.value);
			
			line = br.readLine();

		}
	}

}
