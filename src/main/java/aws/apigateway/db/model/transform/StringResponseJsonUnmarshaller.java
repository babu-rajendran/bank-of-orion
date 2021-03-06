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
 * StringResponse JSON Unmarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class StringResponseJsonUnmarshaller implements Unmarshaller<StringResponse, JsonUnmarshallerContext> {

    public StringResponse unmarshall(JsonUnmarshallerContext context) throws Exception {
        StringResponse stringResponse = new StringResponse();

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
                if (context.testExpression("response", targetDepth)) {
                    context.nextToken();
                    stringResponse.setResponse(context.getUnmarshaller(String.class).unmarshall(context));
                }
            } else if (token == END_ARRAY || token == END_OBJECT) {
                if (context.getLastParsedParentElement() == null || context.getLastParsedParentElement().equals(currentParentElement)) {
                    if (context.getCurrentDepth() <= originalDepth)
                        break;
                }
            }
            token = context.nextToken();
        }

        return stringResponse;
    }

    private static StringResponseJsonUnmarshaller instance;

    public static StringResponseJsonUnmarshaller getInstance() {
        if (instance == null)
            instance = new StringResponseJsonUnmarshaller();
        return instance;
    }
}
