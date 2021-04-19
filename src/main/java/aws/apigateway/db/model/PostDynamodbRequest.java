/**
 * null
 */
package aws.apigateway.db.model;

import java.io.Serializable;
import javax.annotation.Generated;

/**
 * 
 * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/yujv25jhgl-2021-04-19T19:49:35Z/PostDynamodb" target="_top">AWS
 *      API Documentation</a>
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class PostDynamodbRequest extends com.amazonaws.opensdk.BaseRequest implements Serializable, Cloneable {

    private StringRequest stringRequest;

    /**
     * @param stringRequest
     */

    public void setStringRequest(StringRequest stringRequest) {
        this.stringRequest = stringRequest;
    }

    /**
     * @return
     */

    public StringRequest getStringRequest() {
        return this.stringRequest;
    }

    /**
     * @param stringRequest
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public PostDynamodbRequest stringRequest(StringRequest stringRequest) {
        setStringRequest(stringRequest);
        return this;
    }

    /**
     * Returns a string representation of this object. This is useful for testing and debugging. Sensitive data will be
     * redacted from this string using a placeholder value.
     *
     * @return A string representation of this object.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getStringRequest() != null)
            sb.append("StringRequest: ").append(getStringRequest());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (obj instanceof PostDynamodbRequest == false)
            return false;
        PostDynamodbRequest other = (PostDynamodbRequest) obj;
        if (other.getStringRequest() == null ^ this.getStringRequest() == null)
            return false;
        if (other.getStringRequest() != null && other.getStringRequest().equals(this.getStringRequest()) == false)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        hashCode = prime * hashCode + ((getStringRequest() == null) ? 0 : getStringRequest().hashCode());
        return hashCode;
    }

    @Override
    public PostDynamodbRequest clone() {
        return (PostDynamodbRequest) super.clone();
    }

    /**
     * Set the configuration for this request.
     *
     * @param sdkRequestConfig
     *        Request configuration.
     * @return This object for method chaining.
     */
    public PostDynamodbRequest sdkRequestConfig(com.amazonaws.opensdk.SdkRequestConfig sdkRequestConfig) {
        super.sdkRequestConfig(sdkRequestConfig);
        return this;
    }

}
