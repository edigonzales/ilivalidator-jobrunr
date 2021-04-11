package ch.so.agi.ilivalidator;

import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Service
public class AmazonS3StorageService implements StorageService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${app.s3AccessKey}")
    private String s3AccessKey;

    @Value("${app.s3SecretKey}")
    private String s3SecretKey;

    @Value("${app.s3Bucket}")
    private String s3Bucket;

    @Value("${app.s3Region}")
    private String s3Region;

//    private S3Client s3client;
    
    @Override
    public void store(MultipartFile file, String filename) throws IOException {
        AwsCredentialsProvider creds = StaticCredentialsProvider.create(AwsBasicCredentials.create(s3AccessKey, s3SecretKey));
        Region region = Region.of(s3Region);
        S3Client s3client = S3Client.builder()
                .credentialsProvider(creds)
                .region(region)
                .build();
 
        try {
            RequestBody requestBody = RequestBody.fromInputStream(file.getInputStream(), file.getSize());
            PutObjectResponse resp = s3client.putObject(PutObjectRequest.builder().bucket(s3Bucket).key("0000").build(), requestBody);            
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Could not store uploaded file.");
        }  
    }

    @Override
    public void delete(String filename) {
        // TODO
    }

    @Override
    public void init() {
        // z.B. S3Client
    }

    @Override
    public Path load(String filename) {
        return null;
    }
}
