/**
 * null
 */
package aws.apigateway.db.model;

import java.io.Serializable;
import javax.annotation.Generated;
import com.amazonaws.protocol.StructuredPojo;
import com.amazonaws.protocol.ProtocolMarshaller;

/**
 * 
 * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/yujv25jhgl-2021-04-19T19:49:35Z/StringResponse"
 *      target="_top">AWS API Documentation</a>
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class StringResponse implements Serializable, Cloneable, StructuredPojo {

    private String response;

    /**
     * @param response
     */

    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * @return
     */

    public String getResponse() {
        return this.response;
    }

    /**
     * @param response
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public StringResponse response(String response) {
        setResponse(response);
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
        if (getResponse() != null)
            sb.append("Response: ").append(getResponse());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (obj instanceof StringResponse == false)
            return false;
        StringResponse other = (StringResponse) obj;
        if (other.getResponse() == null ^ this.getResponse() == null)
            return false;
        if (other.getResponse() != null && other.getResponse().equals(this.getResponse()) == false)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        hashCode = prime * hashCode + ((getResponse() == null) ? 0 : getResponse().hashCode());
        return hashCode;
    }

    @Override
    public StringResponse clone() {
        try {
            return (StringResponse) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Got a CloneNotSupportedException from Object.clone() " + "even though we're Cloneable!", e);
        }
    }

    @com.amazonaws.annotation.SdkInternalApi
    @Override
    public void marshall(ProtocolMarshaller protocolMarshaller) {
        aws.apigateway.db.model.transform.StringResponseMarshaller.getInstance().marshall(this, protocolMarshaller);
    }
}
