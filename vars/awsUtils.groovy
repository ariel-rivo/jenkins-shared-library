import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sts.StsClient

def createStsClient(String regionName) {
    def region = Region.of(regionName)
    return StsClient.builder()
                    .region(region)
                    .build()
}
