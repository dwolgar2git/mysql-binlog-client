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

package com.github.mysqlbinlog.model.event.extra;

public final class SetColumn extends Column {
    private static final long serialVersionUID = -4284581552411985097L;
    private final long value;

    public SetColumn(int type, long value) {
        super(type);
        this.value = value;
    }

    public Long getValue() {
        return this.value;
    }
    
    @Override
    public String toString() {
        return "SetColumn [value=" + value + "]";
    }
}