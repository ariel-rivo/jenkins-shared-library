package libs
@Grab('com.amazonaws:aws-java-sdk:1.11.89')

import com.amazonaws.services.ec2.AmazonEC2ClientBuilder

    def regionList() {
        ec2 = AmazonEC2ClientBuilder.defaultClient()
        regions = ec2.describeRegions().getRegions()

        regionlist = ""
        regions.each {
            regionlist += it.getRegionName() + "\n"
        }
        return regionlist
    }