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
 * StringRequestMarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
@SdkInternalApi
public class StringRequestMarshaller {

    private static final MarshallingInfo<String> PAYLOAD_BINDING = MarshallingInfo.builder(MarshallingType.STRING).marshallLocation(MarshallLocation.PAYLOAD)
            .marshallLocationName("payload").build();

    private static final StringRequestMarshaller instance = new StringRequestMarshaller();

    public static StringRequestMarshaller getInstance() {
        return instance;
    }

    /**
     * Marshall the given parameter object.
     */
    public void marshall(StringRequest stringRequest, ProtocolMarshaller protocolMarshaller) {

        if (stringRequest == null) {
            throw new SdkClientException("Invalid argument passed to marshall(...)");
        }

        try {
            protocolMarshaller.marshall(stringRequest.getPayload(), PAYLOAD_BINDING);
        } catch (Exception e) {
            throw new SdkClientException("Unable to marshall request to JSON: " + e.getMessage(), e);
        }
    }

}
