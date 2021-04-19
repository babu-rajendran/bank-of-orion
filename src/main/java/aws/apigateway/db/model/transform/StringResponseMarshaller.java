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
 * StringResponseMarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
@SdkInternalApi
public class StringResponseMarshaller {

    private static final MarshallingInfo<String> RESPONSE_BINDING = MarshallingInfo.builder(MarshallingType.STRING).marshallLocation(MarshallLocation.PAYLOAD)
            .marshallLocationName("response").build();

    private static final StringResponseMarshaller instance = new StringResponseMarshaller();

    public static StringResponseMarshaller getInstance() {
        return instance;
    }

    /**
     * Marshall the given parameter object.
     */
    public void marshall(StringResponse stringResponse, ProtocolMarshaller protocolMarshaller) {

        if (stringResponse == null) {
            throw new SdkClientException("Invalid argument passed to marshall(...)");
        }

        try {
            protocolMarshaller.marshall(stringResponse.getResponse(), RESPONSE_BINDING);
        } catch (Exception e) {
            throw new SdkClientException("Unable to marshall request to JSON: " + e.getMessage(), e);
        }
    }

}
