/*
 * Copyright (c) 2016-2018. Uniquid Inc. or its affiliates. All Rights Reserved.
 *
 * License is in the "LICENSE" file accompanying this file.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package com.uniquid.userclient;

/**
 * Return the proper UserClient instance to communicate with a client of specified type
 *
 */
public interface UserClientFactory {

    /**
     * Represent generic configuration used to lookup at runtime the proper client
     */
    interface UserClientFactoryConfiguration {

        /**
         * Return the protocol that the Provider can Talk
         * @return the protocol that the Provider can Talk
         */
        String getProviderProtocol();

        /**
         * Return the provider name
         * @return the provider name
         */
        String getProviderName();

        /**
         * Return the user address
         * @return the user address
         */
        String getUserAddress();

    }
    /**
     * Returns the UserClient compatible with the specified configuration
     * @param configuration the {@link UserClientFactoryConfiguration}
     * @return the {@link UserClient}
     */
    UserClient getUserClient(UserClientFactoryConfiguration configuration);

}
