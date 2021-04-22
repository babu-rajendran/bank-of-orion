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
 * PostDynamodbResult JSON Unmarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class PostDynamodbResultJsonUnmarshaller implements Unmarshaller<PostDynamodbResult, JsonUnmarshallerContext> {

    public PostDynamodbResult unmarshall(JsonUnmarshallerContext context) throws Exception {
        PostDynamodbResult postDynamodbResult = new PostDynamodbResult();

        int originalDepth = context.getCurrentDepth();
        String currentParentElement = context.getCurrentParentElement();
        int targetDepth = originalDepth + 1;

        JsonToken token = context.getCurrentToken();
        if (token == null)
            token = context.nextToken();
        if (token == VALUE_NULL) {
            return postDynamodbResult;
        }

        while (true) {
            if (token == null)
                break;

            postDynamodbResult.setStringResponse(StringResponseJsonUnmarshaller.getInstance().unmarshall(context));
            token = context.nextToken();
        }

        return postDynamodbResult;
    }

    private static PostDynamodbResultJsonUnmarshaller instance;

    public static PostDynamodbResultJsonUnmarshaller getInstance() {
        if (instance == null)
            instance = new PostDynamodbResultJsonUnmarshaller();
        return instance;
    }
}
