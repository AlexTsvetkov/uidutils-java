/*
 * Copyright (c) 2016-2018. Uniquid Inc. or its affiliates. All Rights Reserved.
 *
 * License is in the "LICENSE" file accompanying this file.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package com.uniquid.messages;

import com.google.common.base.MoreObjects;

import java.util.Objects;

/**
 * Represents a Function Response message: a response from a Provider to an User request's.
 */
public class FunctionResponseMessage implements UniquidMessage {

    /**
     * Integer code used when everything is ok
     */
    public static final int RESULT_OK = 0;

    /**
     * Integer code used to signal the the user doesn't have permission to execute the requested function.
     */
    public static final int RESULT_NO_PERMISSION = 2;

    /**
     * Integer code used to signal that the user asked to execute a function that is not available in the environment.
     */
    public static final int RESULT_FUNCTION_NOT_AVAILABLE = 3;

    /**
     * Integer code used to signal a generic error.
     */
    public static final int RESULT_ERROR = 4;


    private long id;

    private String provider = "", result = "";

    private int error;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    @Override
    public MessageType getMessageType() {

        return MessageType.FUNCTION_RESPONSE;

    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof FunctionResponseMessage))
            return false;

        if (this == object)
            return true;

        FunctionResponseMessage functionResponseMessage = (FunctionResponseMessage) object;

        return Objects.equals(id, functionResponseMessage.id) && Objects.equals(provider, functionResponseMessage.provider)
                && Objects.equals(result, functionResponseMessage.result)
                && Objects.equals(error, functionResponseMessage.error);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, provider, result, error);

    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("provider", provider)
                .add("result", result)
                .add("error", error)
                .toString();
    }
}
