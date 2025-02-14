package libs;

@Grab('com.amazonaws:aws-java-sdk:2.30.10')

import com.amazonaws.services.sts.*;
import com.amazonaws.services.ecs.model.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.auth.*;
import com.amazonaws.waiters.*;

public class STS {
    private AmazonSTS client;

    public STS(Regions region, String key, String secret) {
        client = AmazonSTSClientBuilder.standard()
            .withRegion(region)
            .build();
    }

}