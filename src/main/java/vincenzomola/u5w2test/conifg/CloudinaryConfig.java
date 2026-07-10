package vincenzomola.u5w2test.conifg;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary getCloudinaryUploader(@Value("${cloudinary.apikey}") String apikey,
                                            @Value("${cloudinary.name}") String cloudName,
                                            @Value("${cloudinary.secret}") String secret) {

        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apikey);
        config.put("api_secret", secret);

        return new Cloudinary(config);
    }
}
