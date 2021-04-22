/**
 * null
 */
package aws.apigateway.db.model.transform;

import javax.annotation.Generated;

import com.amazonaws.SdkClientException;
import aws.apigateway.db.model.*;

import com.amazonaws.protocol.*;
import com.amazonaws.annotation.SdkInternalApi;

/**
 * PostDynamodbRequestMarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
@SdkInternalApi
public class PostDynamodbRequestMarshaller {

    private static final MarshallingInfo<StructuredPojo> STRINGREQUEST_BINDING = MarshallingInfo.builder(MarshallingType.STRUCTURED)
            .marshallLocation(MarshallLocation.PAYLOAD).isExplicitPayloadMember(true).build();

    private static final PostDynamodbRequestMarshaller instance = new PostDynamodbRequestMarshaller();

    public static PostDynamodbRequestMarshaller getInstance() {
        return instance;
    }

    /**
     * Marshall the given parameter object.
     */
    public void marshall(PostDynamodbRequest postDynamodbRequest, ProtocolMarshaller protocolMarshaller) {

        if (postDynamodbRequest == null) {
            throw new SdkClientException("Invalid argument passed to marshall(...)");
        }

        try {
            protocolMarshaller.marshall(postDynamodbRequest.getStringRequest(), STRINGREQUEST_BINDING);
        } catch (Exception e) {
            throw new SdkClientException("Unable to marshall request to JSON: " + e.getMessage(), e);
        }
    }

}
