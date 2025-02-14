package libs
@GrabResolver(name='central', root='https://repo1.maven.org/maven2/')
@Grab(group='software.amazon.awssdk', module='sts', version='2.20.0')
@Grab(group='software.amazon.awssdk', module='regions', version='2.20.0')
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;
import software.amazon.awssdk.services.sts.model.StsException;
import software.amazon.awssdk.services.sts.model.AssumeRoleResponse;
import software.amazon.awssdk.services.sts.model.Credentials;

Region region = Region.US_EAST_1;
StsClient stsClient = StsClient.builder()
    .region(region)
    .build();

AssumeRoleRequest assumeRoleRequest = AssumeRoleRequest.builder()
    .roleArn("arn:aws:iam::123456789012:role/demo")
    .roleSessionName("session1")
    .build();

try {
    AssumeRoleResponse assumeRoleResponse = stsClient.assumeRole(assumeRoleRequest);
    Credentials credentials = assumeRoleResponse.credentials();
    System.out.println("Access Key ID: " + credentials.accessKeyId());
    System.out.println("Secret Access Key: " + credentials.secretAccessKey());
    System.out.println("Session Token: " + credentials.sessionToken());
} catch (StsException e) {
    System.err.println(e.getMessage());
    System.exit(1);
}