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
public class PostDynamodbResult extends com.amazonaws.opensdk.BaseResult implements Serializable, Cloneable {

    private StringResponse stringResponse;

    /**
     * @param stringResponse
     */

    public void setStringResponse(StringResponse stringResponse) {
        this.stringResponse = stringResponse;
    }

    /**
     * @return
     */

    public StringResponse getStringResponse() {
        return this.stringResponse;
    }

    /**
     * @param stringResponse
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public PostDynamodbResult stringResponse(StringResponse stringResponse) {
        setStringResponse(stringResponse);
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
        if (getStringResponse() != null)
            sb.append("StringResponse: ").append(getStringResponse());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (obj instanceof PostDynamodbResult == false)
            return false;
        PostDynamodbResult other = (PostDynamodbResult) obj;
        if (other.getStringResponse() == null ^ this.getStringResponse() == null)
            return false;
        if (other.getStringResponse() != null && other.getStringResponse().equals(this.getStringResponse()) == false)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        hashCode = prime * hashCode + ((getStringResponse() == null) ? 0 : getStringResponse().hashCode());
        return hashCode;
    }

    @Override
    public PostDynamodbResult clone() {
        try {
            return (PostDynamodbResult) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Got a CloneNotSupportedException from Object.clone() " + "even though we're Cloneable!", e);
        }
    }

}
