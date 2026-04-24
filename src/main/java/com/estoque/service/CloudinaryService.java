package com.estoque.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.UUID;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    public String upload(MultipartFile file){
        try {

            if (file.isEmpty()) {
                throw new RuntimeException("Arquivo vazio");
            }


            if (file.getSize() > 2 * 1024 * 1024) {
                throw new RuntimeException("Arquivo muito grande");
            }


            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                throw new RuntimeException("Arquivo não é uma imagem válida");
            }


            String filename = file.getOriginalFilename();
            if (filename == null ||
                    (!filename.endsWith(".jpg") &&
                            !filename.endsWith(".jpeg") &&
                            !filename.endsWith(".png"))) {
                throw new RuntimeException("Formato inválido");
            }

            String publicId = "produtos/" + UUID.randomUUID();

            Map uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "public_id", publicId,
                            "resource_type", "image"
                    )
            );

            return uploadResult.get("secure_url").toString();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar imagem", e);
        }
    }

}
