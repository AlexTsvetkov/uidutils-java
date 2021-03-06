/*
 * Copyright (c) 2016-2018. Uniquid Inc. or its affiliates. All Rights Reserved.
 *
 * License is in the "LICENSE" file accompanying this file.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package com.uniquid.registry;

import com.uniquid.registry.exception.RegistryException;

/**
 * Data Access Object interface for Registry
 */
public interface RegistryDAO {

    /**
     * Insert a mapping between a provider name and an address
     *
     * @param providerName the name of the provider
     * @param providerAddress the address of the provider
     * @throws RegistryException in case a problem occurs
     */
    void insertMapping(String providerName, String providerAddress) throws RegistryException;

    /**
     * Returns the provider name from its address
     *
     * @param providerAddress the provider address to lookup
     * @return the provider name associated to the address
     * @throws RegistryException in case a problem occurs
     */
    String retrieveProviderName(String providerAddress) throws RegistryException;

}
