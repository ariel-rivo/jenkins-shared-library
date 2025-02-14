// filepath: vars/assumeAwsRole.groovy
@GrabResolver(name='central', root='https://repo1.maven.org/maven2/')
@Grab(group='software.amazon.awssdk', module='sts', version='2.20.0')
@Grab(group='software.amazon.awssdk', module='regions', version='2.20.0')
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sts.StsClient
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest
import software.amazon.awssdk.services.sts.model.StsException
import software.amazon.awssdk.services.sts.model.AssumeRoleResponse
import software.amazon.awssdk.services.sts.model.Credentials

def call(String roleArn, String roleSessionName) {
    Region region = Region.US_EAST_1
    StsClient stsClient = StsClient.builder()
        .region(region)
        .build()

    AssumeRoleRequest assumeRoleRequest = AssumeRoleRequest.builder()
        .roleArn(roleArn)
        .roleSessionName(roleSessionName)
        .build()

    try {
        AssumeRoleResponse assumeRoleResponse = stsClient.assumeRole(assumeRoleRequest)
        Credentials credentials = assumeRoleResponse.credentials()
        println("Access Key ID: " + credentials.accessKeyId())
        println("Secret Access Key: " + credentials.secretAccessKey())
        println("Session Token: " + credentials.sessionToken())
    } catch (StsException e) {
        println(e.getMessage())
        currentBuild.result = 'FAILURE'
    }
}