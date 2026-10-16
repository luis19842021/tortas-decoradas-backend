package com.tortas.decoradas.imagen.infraestructura.adaptadorImagen;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tortas.decoradas.imagen.dominio.puerto.out.PuertoImagen;

@Component
public class AdaptadorImagen implements PuertoImagen {

    private Cloudinary cloudinary;

    public AdaptadorImagen(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @SuppressWarnings({ "unchecked", "null" })
    @Override
    public String guardarImagen(MultipartFile file) {
        List<String> allowedContentTypes = Arrays.asList("jpeg", "png", "jpg", "webp", "avif");

        String extensions = null;

        if (file.getOriginalFilename() != null && file.getOriginalFilename().contains(".")) {

            String[] parts = file.getOriginalFilename().split("\\.");
            extensions = parts[parts.length - 1].toLowerCase();
        }

        if (extensions == null || !allowedContentTypes.contains(extensions)) {
            throw new IllegalArgumentException(
                    "Archivo no permitido. Solo se permiten archivos JPEG, PNG, JPG, WEBP y AVIF.");
        }

        try {

            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("folder","colores"));
            String [] contando = (String []) uploadResult.get("secure_url").toString().split("/");
            String url = contando[contando.length-3]+"/"+contando[contando.length-2]+"/"+contando[contando.length-1];

            return url;

        } catch (Exception e) {
            System.out.println("Error al subir la imagen a Cloudinary: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al subir la imagen a Cloudinary: " + e.getMessage(), e);
        }
    }

}
