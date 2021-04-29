/**
 * null
 */
package aws.apigateway.db;

import java.net.*;
import java.util.*;

import javax.annotation.Generated;

import org.apache.commons.logging.*;

import com.amazonaws.*;
import com.amazonaws.opensdk.*;
import com.amazonaws.opensdk.model.*;
import com.amazonaws.opensdk.protect.model.transform.*;
import com.amazonaws.auth.*;
import com.amazonaws.handlers.*;
import com.amazonaws.http.*;
import com.amazonaws.internal.*;
import com.amazonaws.metrics.*;
import com.amazonaws.regions.*;
import com.amazonaws.transform.*;
import com.amazonaws.util.*;
import com.amazonaws.protocol.json.*;

import com.amazonaws.annotation.ThreadSafe;
import com.amazonaws.client.AwsSyncClientParams;

import com.amazonaws.client.ClientHandler;
import com.amazonaws.client.ClientHandlerParams;
import com.amazonaws.client.ClientExecutionParams;
import com.amazonaws.opensdk.protect.client.SdkClientHandler;
import com.amazonaws.SdkBaseException;

import aws.apigateway.db.model.*;
import aws.apigateway.db.model.transform.*;

/**
 * Client for accessing OrionBankDynamoDB. All service calls made using this
 * client are blocking, and will not return until the service call completes.
 * <p>
 * 
 */
@ThreadSafe
@Generated("com.amazonaws:aws-java-sdk-code-generator")
class OrionBankDynamoDBClient implements OrionBankDynamoDB {

	private final ClientHandler clientHandler;

	private static final com.amazonaws.opensdk.protect.protocol.ApiGatewayProtocolFactoryImpl protocolFactory = new com.amazonaws.opensdk.protect.protocol.ApiGatewayProtocolFactoryImpl(
			new JsonClientMetadata().withProtocolVersion("1.1").withSupportsCbor(false).withSupportsIon(false)
					.withContentTypeOverride("application/json")
					.withBaseServiceExceptionClass(aws.apigateway.db.model.OrionBankDynamoDBException.class));

	/**
	 * Constructs a new client to invoke service methods on OrionBankDynamoDB using
	 * the specified parameters.
	 *
	 * <p>
	 * All service calls made using this new client object are blocking, and will
	 * not return until the service call completes.
	 *
	 * @param clientParams Object providing client parameters.
	 */
	OrionBankDynamoDBClient(AwsSyncClientParams clientParams) {
		this.clientHandler = new SdkClientHandler(new ClientHandlerParams().withClientParams(clientParams));
	}

	/**
	 * @param postDynamodbRequest
	 * @return Result of the PostDynamodb operation returned by the service.
	 * @sample OrionBankDynamoDB.PostDynamodb
	 * @see <a href=
	 *      "http://docs.aws.amazon.com/goto/WebAPI/yujv25jhgl-2021-04-19T19:49:35Z/PostDynamodb"
	 *      target="_top">AWS API Documentation</a>
	 */
	@Override
	public PostDynamodbResult postDynamodb(PostDynamodbRequest postDynamodbRequest) {
		HttpResponseHandler<PostDynamodbResult> responseHandler = protocolFactory.createResponseHandler(
				new JsonOperationMetadata().withPayloadJson(true).withHasStreamingSuccessResponse(false),
				new PostDynamodbResultJsonUnmarshaller());

		HttpResponseHandler<SdkBaseException> errorResponseHandler = createErrorResponseHandler();

		return clientHandler.execute(new ClientExecutionParams<PostDynamodbRequest, PostDynamodbResult>()
				.withMarshaller(new PostDynamodbRequestProtocolMarshaller(protocolFactory))
				.withResponseHandler(responseHandler).withErrorResponseHandler(errorResponseHandler)
				.withInput(postDynamodbRequest));
	}

	/**
	 * Create the error response handler for the operation.
	 * 
	 * @param errorShapeMetadata Error metadata for the given operation
	 * @return Configured error response handler to pass to HTTP layer
	 */
	private HttpResponseHandler<SdkBaseException> createErrorResponseHandler(
			JsonErrorShapeMetadata... errorShapeMetadata) {
		return protocolFactory.createErrorResponseHandler(
				new JsonErrorResponseMetadata().withErrorShapes(Arrays.asList(errorShapeMetadata)));
	}

	@Override
	public void shutdown() {
		clientHandler.shutdown();
	}

}
