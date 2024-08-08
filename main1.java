import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

private static String render(Map<String, Object> model, String templatePath) throws IOException {
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(templatePath)) {
            if (inputStream == null) {
                throw new FileNotFoundException("Resource not found: " + templatePath);
            }
            
            // Alternative way to read all bytes from an InputStream
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }

            // Return the content as a string
            return result.toString(StandardCharsets.UTF_8.name());
        }
    }
