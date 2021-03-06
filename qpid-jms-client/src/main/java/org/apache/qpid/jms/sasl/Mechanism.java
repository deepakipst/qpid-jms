/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.qpid.jms.sasl;

import java.security.Principal;

import javax.security.sasl.SaslException;

/**
 * Interface for all SASL authentication mechanism implementations.
 */
public interface Mechanism extends Comparable<Mechanism> {

    /**
     * Relative priority values used to arrange the found SASL
     * mechanisms in a preferred order where the level of security
     * generally defines the preference.
     */
    public enum PRIORITY {
        LOWEST(0),
        LOWER(1),
        LOW(2),
        MEDIUM(3),
        HIGH(4),
        HIGHER(5),
        HIGHER_STILL(6),
        HIGHEST(7);

        private final int value;

        private PRIORITY(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
       }
    };

    /**
     * @return return the relative priority of this SASL mechanism.
     */
    int getPriority();

    /**
     * @return the well known name of this SASL mechanism.
     */
    String getName();

    /**
     * Create an initial response based on selected mechanism.
     *
     * May be null if there is no initial response.
     *
     * @return the initial response, or null if there isn't one.
     * @throws SaslException if an error occurs computing the response.
     */
    byte[] getInitialResponse() throws SaslException;

    /**
     * Create a response based on a given challenge from the remote peer.
     *
     * @param challenge
     *        the challenge that this Mechanism should response to.
     *
     * @return the response that answers the given challenge.
     * @throws SaslException if an error occurs computing the response.
     */
    byte[] getChallengeResponse(byte[] challenge) throws SaslException;

    /**
     * Sets the user name value for this Mechanism.  The Mechanism can ignore this
     * value if it does not utilize user name in it's authentication processing.
     *
     * @param username
     *        The user name given.
     */
    void setUsername(String username);

    /**
     * Returns the configured user name value for this Mechanism.
     *
     * @return the currently set user name value for this Mechanism.
     */
    String getUsername();

    /**
     * Sets the password value for this Mechanism.  The Mechanism can ignore this
     * value if it does not utilize a password in it's authentication processing.
     *
     * @param username
     *        The user name given.
     */
    void setPassword(String username);

    /**
     * Returns the configured password value for this Mechanism.
     *
     * @return the currently set password value for this Mechanism.
     */
    String getPassword();

    boolean isApplicable(String username, String password, Principal localPrincipal);

}
