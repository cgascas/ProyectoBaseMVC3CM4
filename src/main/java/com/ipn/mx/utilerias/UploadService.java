import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import org.apache.commons.io.FileUtils;


public class UploadService {



    private String acessKey;


    private String secretKey;

    private String clientRegion;


    private String bucketName;


    private Integer maxFileSize;


    public String uploadImage(File file) throws IOException,  AmazonServiceException, SdkClientException{
        byte[] bytes = FileUtils.readFileToByteArray(file);
        String fileObjKeyName = file.getName();


            BasicAWSCredentials awsCreds = new BasicAWSCredentials(acessKey, secretKey);
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();


            ObjectMetadata metadata = new ObjectMetadata();
            URLConnection connection = file.toURL().openConnection();
            String mimeType = connection.getContentType();
            metadata.setContentType(mimeType);
//            metadata.addUserMetadata("x-amz-meta-title", "someTitle");
            metadata.setContentLength(file.length());


            InputStream inputStream = new ByteArrayInputStream(bytes);
            PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, inputStream, metadata);

            s3Client.putObject(request);


            return "https://s3."+clientRegion+".amazonaws.com/"+bucketName+"/"+fileObjKeyName;
        }

}