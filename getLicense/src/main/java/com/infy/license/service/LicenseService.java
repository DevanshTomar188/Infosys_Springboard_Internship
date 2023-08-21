package com.infy.license.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.license.config.AwsConfig;
import com.infy.license.model.Constraint;
import com.infy.license.model.License;
import com.infy.license.model.SoftwareModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LicenseService {
    @Value("${algorithm-name}")
    private String ALGORITHM;
    @Value("${algorithm-secret-key}")
    private String SECRET_KEY;

    @Value("${bucket-name}")
    private String BUCKET_NAME;

    private final AwsConfig awsConfig;
    private final AmazonS3 amazonS3;

    public LicenseService(AwsConfig awsConfig) {
        this.awsConfig = awsConfig;
        this.amazonS3 = awsConfig.s3Client();
    }
    public License getLicenseByLicenseNumber(String licenseNumber) throws Exception {
        List<S3ObjectSummary> licenseFiles = amazonS3.listObjects(BUCKET_NAME).getObjectSummaries();
        boolean flag = false;
        for (S3ObjectSummary file : licenseFiles) {
            String licenseContent = readFromS3Object(amazonS3.getObject(BUCKET_NAME, file.getKey()));
            byte[] encrypted = Base64.getDecoder().decode(licenseContent);
            String jsonString = decrypt(encrypted);
            ObjectMapper objectMapper = new ObjectMapper();
            License license = objectMapper.readValue(jsonString, License.class);

        if (licenseNumber == null || license.getLicenseNumber().equals(licenseNumber)) {
            return license;
        }
    }
        return null;
}

    public String decrypt(byte[] input) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decrypted = cipher.doFinal(input);
        return new String(decrypted, StandardCharsets.UTF_8);
    }
    public String readFromS3Object(S3Object s3Object) throws IOException {
        InputStream inputStream = s3Object.getObjectContent();
        String content = new String(inputStream.readAllBytes());
        inputStream.close();
        return content;
    }
    private byte[] encrypt(byte[] input) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher.doFinal(input);
    }

    private String writeToFile(String fileName, String content) throws IOException {
        byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(contentBytes.length);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(contentBytes);
        amazonS3.putObject(BUCKET_NAME, fileName, inputStream, metadata);
        inputStream.close();
        return content;
    }
}
