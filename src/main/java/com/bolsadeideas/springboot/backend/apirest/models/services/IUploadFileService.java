package com.bolsadeideas.springboot.backend.apirest.models.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {

	public Resource cargar(String nombre) throws MalformedURLException;//Metodo para cargar la imagen indicamos que puede lanzar una exception
	public String copiar(MultipartFile archivo) throws IOException;//Metodo para copiar la imagen a una ruta 
	public boolean eliminar(String nombreFoto);//Metodo para eliminar una imagen
	public Path getPath(String nombreFoto);//Metodo para obtener la ruta de la imagen
	
}
