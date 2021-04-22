/**
 * null
 */
package aws.apigateway.db.model.transform;

import java.math.*;

import javax.annotation.Generated;

import aws.apigateway.db.model.*;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers.*;
import com.amazonaws.transform.*;

import com.fasterxml.jackson.core.JsonToken;
import static com.fasterxml.jackson.core.JsonToken.*;

/**
 * StringRequest JSON Unmarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class StringRequestJsonUnmarshaller implements Unmarshaller<StringRequest, JsonUnmarshallerContext> {

    public StringRequest unmarshall(JsonUnmarshallerContext context) throws Exception {
        StringRequest stringRequest = new StringRequest();

        int originalDepth = context.getCurrentDepth();
        String currentParentElement = context.getCurrentParentElement();
        int targetDepth = originalDepth + 1;

        JsonToken token = context.getCurrentToken();
        if (token == null)
            token = context.nextToken();
        if (token == VALUE_NULL) {
            return null;
        }

        while (true) {
            if (token == null)
                break;

            if (token == FIELD_NAME || token == START_OBJECT) {
                if (context.testExpression("payload", targetDepth)) {
                    context.nextToken();
                    stringRequest.setPayload(context.getUnmarshaller(String.class).unmarshall(context));
                }
            } else if (token == END_ARRAY || token == END_OBJECT) {
                if (context.getLastParsedParentElement() == null || context.getLastParsedParentElement().equals(currentParentElement)) {
                    if (context.getCurrentDepth() <= originalDepth)
                        break;
                }
            }
            token = context.nextToken();
        }

        return stringRequest;
    }

    private static StringRequestJsonUnmarshaller instance;

    public static StringRequestJsonUnmarshaller getInstance() {
        if (instance == null)
            instance = new StringRequestJsonUnmarshaller();
        return instance;
    }
}
