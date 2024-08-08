package org.example.cart;

import spark.Service;

import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Service http = Service.ignite();
        http.port(4567);

        Cart cart = new Cart();

        http.staticFiles.location("/public");

        http.get("/cart", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("items", cart.getItems());
            model.put("total", cart.getTotal());
            return render(model, "public/index.html");
        });

        http.post("/add", (req, res) -> {
            String name = req.queryParams("name");
            double price = Double.parseDouble(req.queryParams("price"));
            cart.addItem(new Item(name, price));
            res.redirect("/cart");
            return null;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) throws IOException {
        // Use class loader to get the resource as an InputStream
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(templatePath)) {
            if (inputStream == null) {
                throw new FileNotFoundException("Resource not found: " + templatePath);
            }
            return new String(inputStream.readAllBytes(), java.nio.charset.StandardCharsets.UTF_8);
        }
    }
    

    // private static String render(Map<String, Object> model, String templatePath) throws IOException {
    //     return new String(Files.readAllBytes(Paths.get(templatePath)), java.nio.charset.StandardCharsets.UTF_8);
    // }
}
