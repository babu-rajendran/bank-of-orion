/**
 * null
 */
package aws.apigateway.db;

import javax.annotation.Generated;

import com.amazonaws.*;
import com.amazonaws.opensdk.*;
import com.amazonaws.opensdk.model.*;
import com.amazonaws.regions.*;

import aws.apigateway.db.model.*;

/**
 * Interface for accessing OrionBankDynamoDB.
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public interface OrionBankDynamoDB {

    /**
     * @param postDynamodbRequest
     * @return Result of the PostDynamodb operation returned by the service.
     * @sample OrionBankDynamoDB.PostDynamodb
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/yujv25jhgl-2021-04-19T19:49:35Z/PostDynamodb"
     *      target="_top">AWS API Documentation</a>
     */
    PostDynamodbResult postDynamodb(PostDynamodbRequest postDynamodbRequest);

    /**
     * @return Create new instance of builder with all defaults set.
     */
    public static OrionBankDynamoDBClientBuilder builder() {
        return new OrionBankDynamoDBClientBuilder();
    }

    /**
     * Shuts down this client object, releasing any resources that might be held open. This is an optional method, and
     * callers are not expected to call it, but can if they want to explicitly release any open resources. Once a client
     * has been shutdown, it should not be used to make any more requests.
     */
    void shutdown();

}
