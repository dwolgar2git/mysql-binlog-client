/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.github.mysql.protocol.deserializer;


import java.io.EOFException;

import com.github.mysql.io.MysqlBinlogByteArrayInputStream;
import com.github.mysql.protocol.model.RawMysqlPacket;
import com.github.mysql.protocol.model.ResponsePacket;

public class RawResponsePacketDeserializer implements ResponsePacketDeserializer {

    @Override
    public ResponsePacket deserialize(MysqlBinlogByteArrayInputStream is) {

        try {
            RawMysqlPacket packet = new RawMysqlPacket();

            int value = is.readInt(3, true);
            if (value < 0) {
            	throw new EOFException();
            }
            packet.setLength(value);
            
            value = is.readInt(1, true);
            if (value < 0) {
            	throw new EOFException();
            }
            packet.setSequence(value);

            int total = 0;
            final byte[] body = new byte[packet.getLength()];
            while (total < body.length) {
            	value = is.read(body, total, body.length - total);
                if (value < 0) {
                	throw new EOFException();
                }

                total += value;
            }
            packet.setRawBody(body);
            
            return packet;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
